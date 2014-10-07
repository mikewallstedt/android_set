package mwallstedt.set;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Hand implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<Card> mCards;

    Hand() {
        mCards = new HashSet<Card>();
    }

    boolean contains(Card card) {
        return mCards.contains(card);
    }

    void add(Card card) {
        mCards.add(card);
    }

    void remove(Card card) {
        mCards.remove(card);
    }

    List<Set<Card>> findSets() {
        List<Set<Card>> sets = new ArrayList<Set<Card>>();
        final int handSize = mCards.size();
        final List<Card> cards = new ArrayList<Card>(mCards);
        for (int firstIndex = 0; firstIndex < handSize; firstIndex++) {
            Card firstCard = cards.get(firstIndex);
            for (int secondIndex = firstIndex + 1; secondIndex < handSize; secondIndex++) {
                Card secondCard = cards.get(secondIndex);
                Card completer = firstCard.getCompleter(secondCard);
                for (int thirdIndex = secondIndex + 1; thirdIndex < handSize; thirdIndex++) {
                    if (completer.equals(cards.get(thirdIndex))) {
                        Set<Card> solution = new HashSet<Card>();
                        solution.add(firstCard);
                        solution.add(secondCard);
                        solution.add(completer);
                        sets.add(solution);
                    }
                }
            }
        }
        return sets;
    }
}
