package de.neusta_sd.roomsmanager.frontend.controllers;

import de.neusta_sd.roomsmanager.facades.ImportFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static de.neusta_sd.roomsmanager.frontend.controllers.ImportApiController.BASE_PATH;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
@Controller
@RequestMapping(BASE_PATH)
public class ImportApiController extends AbstractApiController {

    public static final String BASE_PATH = ApiController.BASE_PATH + "/import";

    private final ImportFacade importFacade;

    public ImportApiController(final ImportFacade importFacade) {
        this.importFacade = importFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void postImport() {
        throw new UnsupportedOperationException();//TODO tello
    }

    private ImportFacade getImportFacade() {
        return importFacade;
    }
}
