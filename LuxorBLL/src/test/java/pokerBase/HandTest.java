package pokerBase;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.DeckException;
import exceptions.HandException;
import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class HandTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private Hand SetHand(ArrayList<Card> setCards, Hand h)
	{
		Object t = null;
		
		try {
			//	Load the Class into 'c'
			Class<?> c = Class.forName("pokerBase.Hand");
			//	Create a new instance 't' from the no-arg Deck constructor
			t = c.newInstance();
			//	Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method msetCardsInHand = c.getDeclaredMethod("setCardsInHand", new Class[]{ArrayList.class});
			//	Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			//	invoke 'msetCardsInHand'
			Object oDraw = msetCardsInHand.invoke(t, setCards);
			
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		h = (Hand)t;
		return h;
		
	}
	/**
	 * This test checks to see if a HandException is throw if the hand only has two cards.
	 * @throws Exception
	 */
	@Test(expected = HandException.class)
	public void TestEvalShortHand() throws Exception {
		
		Hand h = new Hand();
		
		ArrayList<Card> ShortHand = new ArrayList<Card>();
		ShortHand.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		ShortHand.add(new Card(eSuit.CLUBS,eRank.ACE,0));

		h = SetHand(ShortHand,h);
		
		//	This statement should throw a HandException
		h = Hand.EvaluateHand(h);	
	}	
	
	@Test 
 	public void TestIsHandRoyalFlush() { 
 
 		HandScore hs = new HandScore(); 
 		ArrayList<Card> HandRoyalFlush = new ArrayList<Card>(); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.ACE, 0)); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.KING, 0)); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.QUEEN, 0)); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.JACK, 0)); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.TEN, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandRoyalFlush, h); 
 
 		boolean bActualIsHandRoyalFlush = Hand.isHandRoyalFlush(h, hs); 
 		boolean bExpectedIsHandRoyalFlush = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandRoyalFlush, bExpectedIsHandRoyalFlush); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.ACE.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.ACE); 
 	} 
 	@Test 
 	public void TestHandRoyalFlushEval() { 
 
 		ArrayList<Card> HandRoyalFlush = new ArrayList<Card>(); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.ACE, 0)); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.KING, 0)); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.QUEEN, 0)); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.JACK, 0)); 
 		HandRoyalFlush.add(new Card(eSuit.SPADES, eRank.TEN, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandRoyalFlush, h); 
 
 		try { 
 			h = Hand.EvaluateHand(h); 
 		} catch (HandException e) { 
 			e.printStackTrace(); 
 			fail("TestHandRoyalFlush failed"); 
 		} 
 		HandScore hs = h.getHandScore(); 
 		boolean bActualIsHandRoyalFlush = Hand.isHandRoyalFlush(h, hs); 
 		boolean bExpectedIsHandRoyalFlush = true; 

 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandRoyalFlush, bExpectedIsHandRoyalFlush); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.ACE.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.ACE); 
 	} 
 	
	@Test 
 	public void TestIsHandStraightFlush() { 
 
 		HandScore hs = new HandScore(); 
 		ArrayList<Card> HandStraightFlush = new ArrayList<Card>(); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.NINE, 0)); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.EIGHT, 0)); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.SEVEN, 0)); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.SIX, 0)); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.FIVE, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandStraightFlush, h); 
 
 		boolean bActualIsHandStraightFlush = Hand.isHandStraight(h, hs); 
 		boolean bExpectedIsHandStraightFlush = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandStraightFlush, bExpectedIsHandStraightFlush); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.NINE.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE); 
 	} 
 	@Test 
 	public void TestHandStraightFlushEval() { 
 
 		ArrayList<Card> HandStraightFlush = new ArrayList<Card>(); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.NINE, 0)); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.EIGHT, 0)); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.SEVEN, 0)); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.SIX, 0)); 
 		HandStraightFlush.add(new Card(eSuit.SPADES, eRank.FIVE, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandStraightFlush, h); 
 
 		try { 
 			h = Hand.EvaluateHand(h); 
 		} catch (HandException e) { 
 			e.printStackTrace(); 
 			fail("TestHandStraightFlush failed"); 
 		} 
 		HandScore hs = h.getHandScore(); 
 		boolean bActualIsHandStraightFlush = Hand.isHandStraightFlush(h, hs); 
 		boolean bExpectedIsHandStraightFlush = true; 

 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandStraightFlush, bExpectedIsHandStraightFlush); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.NINE.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE); 
 	} 
 	
	@Test 
 	public void TestIsHandPair() { 
 
 		HandScore hs = new HandScore(); 
 		ArrayList<Card> HandPair = new ArrayList<Card>(); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.NINE, 0)); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.FOUR, 0)); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.FOUR, 0)); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.SEVEN, 0)); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.SIX, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandPair, h); 
 
 		boolean bActualIsHandPair = Hand.isHandPair(h, hs); 
 		boolean bExpectedIsHandPair = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandPair, bExpectedIsHandPair); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.FOUR.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE); 
 	} 
 	@Test 
 	public void TestHandPairEval() { 
 
 		ArrayList<Card> HandPair = new ArrayList<Card>(); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.NINE, 0)); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.FOUR, 0)); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.FOUR, 0)); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.SEVEN, 0)); 
 		HandPair.add(new Card(eSuit.SPADES, eRank.SIX, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandPair, h); 
 
 		try { 
 			h = Hand.EvaluateHand(h); 
 		} catch (HandException e) { 
 			e.printStackTrace(); 
 			fail("TestHandPair failed"); 
 		} 
 		HandScore hs = h.getHandScore(); 
 		boolean bActualIsHandPair = Hand.isHandPair(h, hs); 
 		boolean bExpectedIsHandPair = true; 

 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandPair, bExpectedIsHandPair); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.FOUR.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE); 
 	} 
 	
	@Test
	public void TestFourOfAKind() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}
	
	public void TestFourOfAKindEval() {
		
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestFourOfAKindEval failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}	

	@Test
	public void TestIsHandFullHouse() {

		HandScore hs = new HandScore();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		FullHouse.add(new Card(eSuit.HEARTS, eRank.ACE, 0));
		FullHouse.add(new Card(eSuit.HEARTS, eRank.KING, 0));
		FullHouse.add(new Card(eSuit.SPADES, eRank.KING, 0));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING, 0));

		Hand h = new Hand();
		h = SetHand(FullHouse, h);

		boolean bActualIsHandFullHouse = Hand.isHandFullHouse(h, hs);
		boolean bExpectedIsHandFullHouse = true;

		// Did this evaluate to Four of a Kind?
		assertEquals( bExpectedIsHandFullHouse, bActualIsHandFullHouse);
		// Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(), eRank.KING.getiRankNbr());
		// FOAK has one kicker. Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		// FOAK has one kicker. Was it a King?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.ACE);
	}
	
	@Test
	public void TestFullHouseEval() {

		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		FullHouse.add(new Card(eSuit.HEARTS, eRank.ACE, 0));
		FullHouse.add(new Card(eSuit.HEARTS, eRank.KING, 0));
		FullHouse.add(new Card(eSuit.SPADES, eRank.KING, 0));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING, 0));

		Hand h = new Hand();
		h = SetHand(FullHouse, h);

		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {
			e.printStackTrace();
			fail("TestFullHouse failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandFullHouse = Hand.isHandFullHouse(h, hs);
		boolean bExpectedIsHandFullHouse = true;

		// Did this evaluate to Four of a Kind?
		assertEquals( bExpectedIsHandFullHouse, bActualIsHandFullHouse);
		// Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(), eRank.KING.getiRankNbr());
		// FOAK has one kicker. Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		// FOAK has one kicker. Was it a King?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.ACE);
	}
	
	@Test
	public void TestIsHandTwoPair() {

		HandScore hs = new HandScore();
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.CLUBS, eRank.FIVE, 0));
		TwoPair.add(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		TwoPair.add(new Card(eSuit.DIAMONDS, eRank.FOUR, 0));
		TwoPair.add(new Card(eSuit.SPADES, eRank.THREE, 0));
		TwoPair.add(new Card(eSuit.CLUBS, eRank.THREE, 0));

		Hand h = new Hand();
		h = SetHand(TwoPair, h);

		boolean bActualIsHandTwoPair = Hand.isHandTwoPair(h, hs);
		boolean bExpectedIsHandTwoPair = true;

		// Did this evaluate to Four of a Kind?
		assertEquals( bExpectedIsHandTwoPair, bActualIsHandTwoPair);
		// Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(), eRank.THREE.getiRankNbr());
		// FOAK has one kicker. Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		// FOAK has one kicker. Was it a King?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.FIVE);
	}
	
	@Test 
 	public void TestHandTwoPairEval() { 
 
 		ArrayList<Card> TwoPair = new ArrayList<Card>(); 
 		TwoPair.add(new Card(eSuit.CLUBS, eRank.FIVE, 0)); 
 		TwoPair.add(new Card(eSuit.HEARTS, eRank.FIVE, 0)); 
 		TwoPair.add(new Card(eSuit.DIAMONDS, eRank.FOUR, 0)); 
 		TwoPair.add(new Card(eSuit.SPADES, eRank.THREE, 0)); 
 		TwoPair.add(new Card(eSuit.CLUBS, eRank.THREE, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(TwoPair, h); 
 
 		try { 
 			h = Hand.EvaluateHand(h); 
 		} catch (HandException e) { 
 			e.printStackTrace(); 
 			fail("TestHandTwoPair failed"); 
 		} 
 		HandScore hs = h.getHandScore(); 
 		boolean bActualIsHandTwoPair = Hand.isHandTwoPair(h, hs); 
 		boolean bExpectedIsHandTwoPair = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandTwoPair, bExpectedIsHandTwoPair); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.FIVE.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.DIAMONDS); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.FOUR); 
 	}

	@Test 
 	public void TestIsHandFlush() { 
 
 		HandScore hs = new HandScore(); 
 		ArrayList<Card> HandFlush = new ArrayList<Card>(); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.ACE, 0)); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.KING, 0)); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.FIVE, 0)); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.FOUR, 0)); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.TWO, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandFlush, h); 
 
 		boolean bActualIsHandFlush = Hand.isHandFlush(h, hs); 
 		boolean bExpectedIsHandFlush = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandFlush, bExpectedIsHandFlush); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.ACE.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.HEARTS); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.ACE); 
 	} 
	
 	@Test 
 	public void TestHandFlushEval() { 
 
 		ArrayList<Card> HandFlush = new ArrayList<Card>(); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.ACE, 0)); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.KING, 0)); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.FIVE, 0)); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.FOUR, 0)); 
 		HandFlush.add(new Card(eSuit.HEARTS, eRank.TWO, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandFlush, h); 
 
 		try { 
 			h = Hand.EvaluateHand(h); 
 		} catch (HandException e) { 
 			e.printStackTrace(); 
 			fail("TestHandFlush failed"); 
 		} 
 		HandScore hs = h.getHandScore(); 
 		boolean bActualIsHandFlush = Hand.isHandFlush(h, hs); 
 		boolean bExpectedIsHandFlush = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandFlush, bExpectedIsHandFlush); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.ACE.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.HEARTS); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.ACE); 
 	} 
 	
 	@Test 
 	public void TestIsHandStraight() { 
 
 		HandScore hs = new HandScore(); 
 		ArrayList<Card> HandStraight = new ArrayList<Card>(); 
 		HandStraight.add(new Card(eSuit.HEARTS, eRank.JACK, 0)); 
 		HandStraight.add(new Card(eSuit.SPADES, eRank.TEN, 0)); 
 		HandStraight.add(new Card(eSuit.CLUBS, eRank.NINE, 0)); 
 		HandStraight.add(new Card(eSuit.DIAMONDS, eRank.EIGHT, 0)); 
 		HandStraight.add(new Card(eSuit.HEARTS, eRank.SEVEN, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandStraight, h); 
 
 		boolean bActualIsHandStraight = Hand.isHandStraight(h, hs); 
 		boolean bExpectedIsHandStraight = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandStraight, bExpectedIsHandStraight); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.JACK.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.HEARTS); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.JACK); 
 	} 
 	
 	@Test 
 	public void TestHandStraightEval() { 
 
 		ArrayList<Card> HandStraight = new ArrayList<Card>(); 
 		HandStraight.add(new Card(eSuit.HEARTS, eRank.JACK, 0)); 
 		HandStraight.add(new Card(eSuit.SPADES, eRank.TEN, 0)); 
 		HandStraight.add(new Card(eSuit.CLUBS, eRank.NINE, 0)); 
 		HandStraight.add(new Card(eSuit.DIAMONDS, eRank.EIGHT, 0)); 
 		HandStraight.add(new Card(eSuit.HEARTS, eRank.SEVEN, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandStraight, h); 
 
 		try { 
 			h = Hand.EvaluateHand(h); 
 		} catch (HandException e) { 
 			e.printStackTrace(); 
 			fail("TestHandStraight failed"); 
 		} 
 		HandScore hs = h.getHandScore(); 
 		boolean bActualIsHandStraight = Hand.isHandStraight(h, hs); 
 		boolean bExpectedIsHandStraight = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandStraight, bExpectedIsHandStraight); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.JACK.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.HEARTS); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.JACK); 
 	} 
 	
 	@Test 
 	public void TestIsHandThreeOfAKind() { 
 
 		HandScore hs = new HandScore(); 
 		ArrayList<Card> HandThreeOfAKind = new ArrayList<Card>(); 
 		HandThreeOfAKind.add(new Card(eSuit.HEARTS, eRank.NINE, 0)); 
 		HandThreeOfAKind.add(new Card(eSuit.SPADES, eRank.NINE, 0)); 
 		HandThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.NINE, 0)); 
 		HandThreeOfAKind.add(new Card(eSuit.DIAMONDS, eRank.THREE, 0)); 
 		HandThreeOfAKind.add(new Card(eSuit.HEARTS, eRank.TWO, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandThreeOfAKind, h); 
 
 		boolean bActualIsHandThreeOfAKind = Hand.isHandThreeOfAKind(h, hs); 
 		boolean bExpectedIsHandThreeOfAKind = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandThreeOfAKind, bExpectedIsHandThreeOfAKind); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.NINE.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.DIAMONDS); 
 		// FOAK has one kicker. Was it a King? 
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.THREE); 
 	} 
 	
 	@Test 
 	public void TestHandThreeOfAKindEval() { 
 
 		ArrayList<Card> HandThreeOfAKind = new ArrayList<Card>(); 
 		HandThreeOfAKind.add(new Card(eSuit.HEARTS, eRank.NINE, 0)); 
		HandThreeOfAKind.add(new Card(eSuit.SPADES, eRank.NINE, 0)); 
 		HandThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.NINE, 0)); 
 		HandThreeOfAKind.add(new Card(eSuit.DIAMONDS, eRank.THREE, 0)); 
 		HandThreeOfAKind.add(new Card(eSuit.HEARTS, eRank.TWO, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandThreeOfAKind, h); 
 
 		try { 
 			h = Hand.EvaluateHand(h); 
 		} catch (HandException e) { 
 			e.printStackTrace(); 
 			fail("TestHandThreeOfAKind failed"); 
 		} 
 		HandScore hs = h.getHandScore(); 
 		boolean bActualIsHandThreeOfAKind = Hand.isHandThreeOfAKind(h, hs); 
 		boolean bExpectedIsHandThreeOfAKind = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandThreeOfAKind, bExpectedIsHandThreeOfAKind); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.NINE.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.HEARTS); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE); 
 	} 
 	
 	@Test 
 	public void TestIsHandHighCard() { 
 
 		HandScore hs = new HandScore(); 
 		ArrayList<Card> HandHighCard = new ArrayList<Card>(); 
 		HandHighCard.add(new Card(eSuit.SPADES, eRank.QUEEN, 0)); 
 		HandHighCard.add(new Card(eSuit.CLUBS, eRank.NINE, 0)); 
 		HandHighCard.add(new Card(eSuit.HEARTS, eRank.SEVEN, 0)); 
 		HandHighCard.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0)); 
 		HandHighCard.add(new Card(eSuit.HEARTS, eRank.THREE, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandHighCard, h); 
 
 		boolean bActualIsHandHighCard = Hand.isHandHighCard(h, hs); 
 		boolean bExpectedIsHandHighCard = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandHighCard, bExpectedIsHandHighCard); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.QUEEN.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.QUEEN); 
 	} 
 	
 	@Test 
 	public void TestHandHighCardEval() { 
 
 		ArrayList<Card> HandHighCard = new ArrayList<Card>(); 
 		HandHighCard.add(new Card(eSuit.SPADES, eRank.QUEEN, 0)); 
 		HandHighCard.add(new Card(eSuit.CLUBS, eRank.NINE, 0)); 
 		HandHighCard.add(new Card(eSuit.HEARTS, eRank.SEVEN, 0)); 
 		HandHighCard.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0)); 
 		HandHighCard.add(new Card(eSuit.HEARTS, eRank.THREE, 0)); 
 
 		Hand h = new Hand(); 
 		h = SetHand(HandHighCard, h); 
 
 		try { 
 			h = Hand.EvaluateHand(h); 
 		} catch (HandException e) { 
 			e.printStackTrace(); 
 			fail("TestHandHighCard failed"); 
 		} 
 		HandScore hs = h.getHandScore(); 
 		boolean bActualIsHandHighCard = Hand.isHandHighCard(h, hs); 
 		boolean bExpectedIsHandHighCard = true; 
 
 		// Did this evaluate to Four of a Kind? 
 		assertEquals(bActualIsHandHighCard, bExpectedIsHandHighCard); 
 		// Was the four of a kind an Ace? 
 		assertEquals(hs.getHiHand(), eRank.QUEEN.getiRankNbr()); 
 		// FOAK has one kicker. Was it a Club? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.SPADES); 
 		// FOAK has one kicker. Was it a King? 
 		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.QUEEN); 
 	} 	
} 
