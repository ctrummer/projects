
package com.spielwiese.simplex;

import com.ssp.common.logging.ILogger;
import com.ssp.common.logging.LogHelper;


public class PickByLightStation {

  private static final ILogger LOG = LogHelper.getLogger();
  
  private final int numberOfLines;
  private final int numberOfLocationsPerLine;
  
  public PickByLightStation(int numberOfLines, int numberOfLocationsPerLine) {
    super();
   
    if (numberOfLines < 1) {
     throw new IllegalArgumentException("Number of lines have to be 1 or greater.");
    }
    if (numberOfLocationsPerLine < 1) {
      throw new IllegalArgumentException("Number of locations per line have to be 1 or greater.");
    }
    
    this.numberOfLines = numberOfLines;
    this.numberOfLocationsPerLine = numberOfLocationsPerLine;
  }
  
  public int getPlaceNumber(int lineNr, int column) {
    throw new RuntimeException("Not implemented yet!");
    int placeNumber = (lineNr-1)
    
    return null;
  }
  
  
  
  

}


//---------------------------- Revision History ----------------------------
//$Log$
//
