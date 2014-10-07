package mwallstedt.set;

interface CardPickerHostActivity {
    boolean handContains(Card card);
    void onCardChosen(Card card);
}
