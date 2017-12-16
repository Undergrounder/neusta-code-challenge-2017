package de.neustasd.roomsmanager.facades.imprt.csv.parser.converter.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.services.NameAdditionService;
import de.neustasd.roomsmanager.core.services.TitleService;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.converter.PersonStringConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/** Created by Adrian Tello on 09/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class PersonStringConverterImplUnitTest {

    @Mock private TitleService titleService;

    @Mock private NameAdditionService nameAdditionService;

    private PersonStringConverter personStringConverter;

    @Before
    public void setUp() throws Exception {
        personStringConverter = new PersonStringConverterImpl(titleService, nameAdditionService);
    }

    @Test(expected = Converter.ConversionException.class)
    public void testConvertNull() {
        // Test
        personStringConverter.convert(null);
    }

    @Test(expected = Converter.ConversionException.class)
    public void testConvertEmpty() {
        // Test
        personStringConverter.convert(null);
    }

    @Test
    public void testConvertDennisFischer() {
        // Prepare
        final String personString = "Dennis Fischer (dfischer)";

        // Test
        final ImportCsvParser.CsvPersonData csvPersonData = personStringConverter.convert(personString);

        // Verify
        assertConversion(csvPersonData, null, "Dennis", null, "Fischer", "dfischer");
    }

    @Test
    public void testConvertIftikarAhmadKhan() {
        // Prepare
        final String personString = "Iftikar Ahmad Khan (ikhan)";

        // Test
        final ImportCsvParser.CsvPersonData csvPersonData = personStringConverter.convert(personString);

        // Verify
        assertConversion(csvPersonData, null, "Iftikar Ahmad", null, "Khan", "ikhan");
    }

    @Test
    public void testConvertAlexanderJamesCole() {
        // Prepare
        final String personString = "Alexander James Cole (acole)";

        // Test
        final ImportCsvParser.CsvPersonData csvPersonData = personStringConverter.convert(personString);

        // Verify
        assertConversion(csvPersonData, null, "Alexander James", null, "Cole", "acole");
    }

    @Test
    public void testConvertThomasVonGostomski() {
        // Prepare
        final String personString = "Thomas von Gostomski (tgostomski)";

        when(nameAdditionService.existsNameAddition("von")).thenReturn(true);

        // Test
        final ImportCsvParser.CsvPersonData csvPersonData = personStringConverter.convert(personString);

        // Verify
        assertConversion(csvPersonData, null, "Thomas", "von", "Gostomski", "tgostomski");
    }

    @Test
    public void testConvertEtienneDeRuffray() {
        // Prepare
        final String personString = "Etienne de Ruffray (eruffray)";

        when(nameAdditionService.existsNameAddition("de")).thenReturn(true);

        // Test
        final ImportCsvParser.CsvPersonData csvPersonData = personStringConverter.convert(personString);

        // Verify
        assertConversion(csvPersonData, null, "Etienne", "de", "Ruffray", "eruffray");
    }

    @Test
    public void testConvertDrDennisKrannich() {
        // Prepare
        final String personString = "Dr. Dennis Krannich (dkrannich)";

        when(titleService.existsTitleByName("Dr.")).thenReturn(true);

        // Test
        final ImportCsvParser.CsvPersonData csvPersonData = personStringConverter.convert(personString);

        // Verify
        assertConversion(csvPersonData, "Dr.", "Dennis", null, "Krannich", "dkrannich");
    }

    private void assertConversion(
        final ImportCsvParser.CsvPersonData csvPersonData,
        final String title,
        final String firstName,
        final String nameAddition,
        final String lastName,
        final String ldapUser) {
        assertNotNull(csvPersonData);

        assertEquals(title, csvPersonData.getTitle());
        assertEquals(firstName, csvPersonData.getFirstName());
        assertEquals(nameAddition, csvPersonData.getNameAddition());
        assertEquals(lastName, csvPersonData.getLastName());
        assertEquals(ldapUser, csvPersonData.getLdapUser());
    }
}