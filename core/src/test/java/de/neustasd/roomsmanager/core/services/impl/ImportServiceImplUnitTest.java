package de.neustasd.roomsmanager.core.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.entities.repositories.PersonRepository;
import de.neustasd.roomsmanager.core.entities.repositories.RoomRepository;
import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.converters.PersonDataConverter;
import de.neustasd.roomsmanager.core.services.converters.RoomDataConverter;
import de.neustasd.roomsmanager.core.services.data.ImportData;
import de.neustasd.roomsmanager.core.services.data.ImportResultData;
import de.neustasd.roomsmanager.core.services.data.PersonData;
import de.neustasd.roomsmanager.core.services.data.RoomData;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit-Test for the ImportServiceImpl class
 *
 * @author Adrian Tello
 */
@RunWith(MockitoJUnitRunner.class)
public class ImportServiceImplUnitTest {
    private ImportService importService;

    @Mock
    private Validator validator;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private RoomDataConverter roomDataConverter;

    @Mock
    private PersonDataConverter personDataConverter;


    @Before
    public void setUp() throws Exception {
        importService = new ImportServiceImpl(validator,
                roomRepository,
                personRepository,
                roomDataConverter,
                personDataConverter);
    }

    @Test
    public void testEmpty() throws ImportService.ImportException {
        //Prepare
        final ImportData importData = ImportData.builder().build();

        //Test
        final ImportResultData importResultData = importService.importData(importData);

        //Verify
        assertNotNull(importResultData);
    }

    @Test
    public void testMultiple() throws ImportService.ImportException {
        //Prepare
        final PersonData mnolteData = PersonData.builder()
                .firstName("Markus")
                .lastName("Nolte")
                .ldapUser("mnolte")
                .build();

        final RoomData room1101Data = RoomData.builder()
                .number("1101")
                .personData(mnolteData)
                .build();

        final PersonData cfleuterData = PersonData.builder()
                .firstName("Claudia")
                .lastName("Fleuter")
                .ldapUser("cfleuter")
                .build();

        final PersonData sstrodthoffData = PersonData.builder()
                .firstName("Sabine")
                .lastName("Strodthoff")
                .ldapUser("sstrodthoff")
                .build();

        final RoomData room1113Data = RoomData.builder()
                .number("1113")
                .personData(cfleuterData)
                .personData(sstrodthoffData)
                .build();

        final ImportData importData = ImportData.builder()
                .roomData(room1101Data)
                .roomData(room1113Data)
                .build();

        final Person mnoltePerson = new Person();
        mnoltePerson.setFirstName("Markus");
        mnoltePerson.setLastName("Nolte");
        mnoltePerson.setLdapUser("mnolte");

        final Room room1101 = new Room();
        room1101.setNumber("1101");
        room1101.setPeople(Collections.singletonList(mnoltePerson));

        final Person cfleuterPerson = new Person();
        cfleuterPerson.setFirstName("Claudia");
        cfleuterPerson.setLastName("Fleuter");
        cfleuterPerson.setLdapUser("cfleuter");

        final Person sstrodthoffPerson = new Person();
        sstrodthoffPerson.setFirstName("Sabine");
        sstrodthoffPerson.setLastName("Strodthoff");
        sstrodthoffPerson.setLdapUser("sstrodthoff");

        final Room room1103 = new Room();
        room1101.setNumber("1103");
        room1101.setPeople(Arrays.asList(cfleuterPerson, sstrodthoffPerson));

        when(roomDataConverter.convert(room1101Data)).thenReturn(room1101);
        when(personDataConverter.convert(mnolteData)).thenReturn(mnoltePerson);

        when(roomDataConverter.convert(room1113Data)).thenReturn(room1103);
        when(personDataConverter.convert(cfleuterData)).thenReturn(cfleuterPerson);
        when(personDataConverter.convert(sstrodthoffData)).thenReturn(sstrodthoffPerson);

        //Test
        final ImportResultData importResultData = importService.importData(importData);

        //Verify
        assertNotNull(importResultData);
        assertEquals(3, importResultData.getPersons());
        assertEquals(2, importResultData.getRooms());
    }

    @Test(expected = ImportService.ImportException.class)
    public void testConverterException() throws ImportService.ImportException {
        //Prepare
        final RoomData roomData = RoomData.builder()
                .number("1234")
                .build();

        final ImportData importData = ImportData.builder()
                .roomData(roomData)
                .build();

        final Converter.ConversionException conversionException = new Converter.ConversionException("Invalid room");
        when(roomDataConverter.convert(roomData)).thenThrow(conversionException);

        //Test
        importService.importData(importData);
    }

    @Test(expected = ImportService.InvalidImportDataException.class)
    public void testInvalid() throws ImportService.ImportException {
        //Prepare
        final RoomData roomData = RoomData.builder().number("1234").build();
        final ImportData importData = ImportData.builder()
                .roomData(roomData)
                .roomData(roomData)
                .build();

        @SuppressWarnings("unchecked")
        final ConstraintViolation<ImportData> constraintViolation = mock(ConstraintViolation.class);

        final Set<ConstraintViolation<ImportData>> constraintViolationSet = new HashSet<>(1);
        constraintViolationSet.add(constraintViolation);

        when(validator.validate(importData)).thenReturn(constraintViolationSet);

        //Test
        importService.importData(importData);
    }
}
