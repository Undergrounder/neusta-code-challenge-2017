package de.neustasd.roomsmanager.core.services.converters.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.entities.Title;
import de.neustasd.roomsmanager.core.entities.repositories.TitleRepository;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.converters.TitleNameConverter;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/** Created by Adrian Tello on 01/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class TitleNameConverterImplUnitTest {

  @Mock private TitleRepository titleRepository;

  @InjectMocks private TitleNameConverter titleNameConverter = new TitleNameConverterImpl();

  @Test(expected = Converter.ConversionException.class)
  public void testNonExistentTitleName() throws Converter.ConversionException {
    // Test
    titleNameConverter.convert("non-existent");
  }

  @Test
  public void testExistent() throws Converter.ConversionException {
    // Prepare
    final String name = "name";

    {
      final Title targetTitle = new Title();
      targetTitle.setName(name);
      when(titleRepository.findOneByName(name)).thenReturn(Optional.of(targetTitle));
    }

    // Test
    final Title targetTitle = titleNameConverter.convert(name);

    // Verify
    assertNotNull(targetTitle);
    assertEquals(name, targetTitle.getName());
  }
}
