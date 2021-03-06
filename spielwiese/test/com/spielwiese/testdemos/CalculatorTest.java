/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id$
 * <br /> <br />
 *
 */

package com.spielwiese.testdemos;

import static org.mockito.Mockito.mock;

import org.junit.AfterClass;
import org.junit.Test;

import com.spielwiese.trial.Calculator;
import com.spielwiese.trial.DummyContainer;

/**
 * This is the class header. The first sentence (ending with "."+SPACE) is important, because it is
 * used summary in the package overview pages.<br />
 * <br />
 * 
 * @author ctr
 * @version $Revision$
 */

public class CalculatorTest {

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Test
  public void testAdd() throws Exception {

    DummyContainer containerAugend = mock(DummyContainer.class);
    DummyContainer containerAddend = mock(DummyContainer.class);

    //		when(containerAugend.getValue()).thenReturn(5);

    Calculator calc = new Calculator();
    //    calc.add(augend, addend);
    //    assertEquals(12, calc.add(7, 5));
    throw new RuntimeException("not yet implemented");
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
