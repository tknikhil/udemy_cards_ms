package com.ultimate.cards.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ultimate.cards.config.CardsServiceConfig;
import com.ultimate.cards.model.Cards;
import com.ultimate.cards.model.Customer;
import com.ultimate.cards.model.Properties;
import com.ultimate.cards.repo.CardRepository;

@RestController
public class CardController {
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	CardsServiceConfig cardsConfig;
	
	@PostMapping("/myCard")
	public List<Cards> getCardDetails(@RequestHeader("eazybank-correlation-id")String correlationId,@RequestBody Customer customer){
		List <Cards> cards = cardRepository.findByCustomerId(customer.getCustomerId());
		
		if(cards!=null)
			return cards;
		else
			return null;
	}

	@GetMapping("/cards/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
				cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
}
