//package com.trueguiders;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class TrueguidersApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}

package com.trueguiders;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
		UserOperationsTest.class,                // 1. Kullanıcı Kayıt/Giriş
		AlgorithmLogicTest.class,                // 3. Algoritma ve Rota
		PlanAndRatingTest.class,                 // 4. Servis Seviyesi Kayıt
		TravelPlanControllerIntegrationTest.class, // 5. API Seviyesi Kayıt (YENİ EKLENEN)

})
public class TrueguidersApplicationTests {
}
