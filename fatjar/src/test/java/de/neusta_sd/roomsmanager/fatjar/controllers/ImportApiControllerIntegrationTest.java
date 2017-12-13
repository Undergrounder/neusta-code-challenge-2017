package de.neusta_sd.roomsmanager.fatjar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.services.PersonsService;
import de.neusta_sd.roomsmanager.core.services.RoomsService;
import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
import de.neusta_sd.roomsmanager.frontend.dto.ExceptionDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Adrian Tello on 09/12/2017.
 */

public class ImportApiControllerIntegrationTest extends AbstractApiIntegrationTest{

    @Test
    public void testEmptyFile() throws IOException {
        //Test
        final ResponseEntity<ImportResultDto> responseEntity = postResource("/imports/empty.csv");

        //Verify
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

        final ImportResultDto importResultDto = responseEntity.getBody();
        assertNotNull(importResultDto);
        assertEquals(0, importResultDto.getRooms());
        assertEquals(0, importResultDto.getPersons());

        //Verify database
        assertNoRoomsInDatabase();
        assertNoPersonsInDatabase();
    }

    @Test
    public void testExampleFile() throws IOException {
        //Test
        final ResponseEntity<ImportResultDto> responseEntity = importSitzplanFile();

        //Verify
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

        final ImportResultDto importResultDto = responseEntity.getBody();
        assertNotNull(importResultDto);
        assertEquals(15, importResultDto.getRooms());
        assertEquals(49, importResultDto.getPersons());

        //Verify database
        final List<Room> roomList = getRoomsService().findAll();
        assertNotNull(roomList);
        assertEquals(15, roomList.size());

        final List<Person> personList = getPersonsService().findAll();
        assertNotNull(personList);
        assertEquals(49, personList.size());
    }

    @Test
    public void importChain() throws IOException {
        testExampleFile();
        testEmptyFile();
    }

    @Test
    public void importFileDuplicatedPerson() throws IOException {
        doTestValidationFailed(this::importSitzplanDuplicatedPersonFile, 3);
    }

    @Test
    public void testImportFileDuplicatedRoom() throws IOException {
        doTestValidationFailed(this::importSitzplanDuplicatedRoomFile, 2);
    }

    @Test
    public void testImportFileInvalidRoomNumber() throws IOException {
        doTestValidationFailed(this::importSitzplanInvalidRoomNumberFile, 4);
    }

    @Test
    public void testInvalidMethod(){
        doTestInvalidMethod("/api/import");
    }

    protected void doTestValidationFailed(Runnable runnable, int expectedBodyCode) throws IOException {
        doTestException(runnable, HttpStatus.BAD_REQUEST, expectedBodyCode);
    }

    private ResponseEntity<ImportResultDto> importSitzplanDuplicatedRoomFile(){
        return postResource("/imports/sitzplan_duplicated_room.csv");
    }

    private ResponseEntity<ImportResultDto> importSitzplanDuplicatedPersonFile(){
        return postResource("/imports/sitzplan_duplicated_person.csv");
    }

    private ResponseEntity<ImportResultDto> importSitzplanInvalidRoomNumberFile(){
        return postResource("/imports/sitzplan_invalid_room_number.csv");
    }
}
