package de.neustasd.roomsmanager.core.services.converters.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.entities.NameAddition;
import de.neustasd.roomsmanager.core.entities.repositories.NameAdditionRepository;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.converters.NameAdditionNameConverter;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/** Created by Adrian Tello on 01/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class NameAdditionNameConverterImplUnitTest {

  @Mock private NameAdditionRepository nameAdditionRepository;

  @InjectMocks
  private NameAdditionNameConverter nameAdditionNameConverter = new NameAdditionNameConverterImpl();

  @Test(expected = Converter.ConversionException.class)
  public void testNonExistent() throws Converter.ConversionException {
    // Test
    nameAdditionNameConverter.convert("non-existent");
  }

  @Test
  public void testExistent() throws Converter.ConversionException {
    // Prepare
    final String name = "name";

    {
      final NameAddition targetNameAddition = new NameAddition();
      targetNameAddition.setName(name);
      when(nameAdditionRepository.findOneByName(name)).thenReturn(Optional.of(targetNameAddition));
    }

    // Test
    final NameAddition targetNameAddition = nameAdditionNameConverter.convert(name);

    // Verify
    assertNotNull(targetNameAddition);
    assertEquals(name, targetNameAddition.getName());
  }
}
