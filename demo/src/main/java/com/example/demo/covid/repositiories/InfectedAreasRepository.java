/*******************************************************************************
 * Copyright     : Copyright (c)  2020 Honeywell Inc. All rights reserved.
 *                 This work contains trade secrets and confidential materials
 *                 of Honeywell Inc., and its use or disclosure in whole or in 
 *                 part without express written permission of Honeywell Inc. 
 *                 is prohibited.
 *
 * Name          :  InfectedAreasRepository.java.java
 * 
 * Project Title :  demo 
 *  
 * Description	:
 * 
 * 
 * Company       : Honeywell Connected Enterprise, Hyderabad
 * 
 * @Author       : H156833
 * 
 * @Date		 : Apr 4, 2020
 *
 * @Version      : Version 1.0
 * 
 * Modification History:
 * 
 * By            : <Modifier Name>
 * On            : <Modified Date>
 * Description   : <Description of modification>
 ******************************************************************************/
package com.example.demo.covid.repositiories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.covid.dtos.CovidLocation;

/**
 * @author H156833
 *
 */
public interface InfectedAreasRepository extends MongoRepository<CovidLocation,String>{

}