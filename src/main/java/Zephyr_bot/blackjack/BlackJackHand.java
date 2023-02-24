package Zephyr_bot.blackjack;

import Zephyr_bot.card.Card;
import Zephyr_bot.card.CardHand;
import Zephyr_bot.card.CardRank;

public class BlackJackHand extends CardHand {

    /**
     * calculates the value of the hand
     *
     * @return points
     */
    public int getValue() {
        int value = 0;
        int aces = 0;
        for (Card card : cardsInHand) {
            if (card.getRank().equals(CardRank.ACE)) {
                aces++;
            }
            value += card.getRank().getValue();
        }
        while (aces > 0 && value > 21) {
            aces--;
            value -= 10;
        }
        return value;
    }

    public String printHand() {
        StringBuilder hand = new StringBuilder();
        for (Card card : cardsInHand) {
            hand.append(card.toEmote()).append(" ");
        }
        return hand.toString();
    }
}