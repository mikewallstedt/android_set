package mwallstedt.set;

import java.io.Serializable;

public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final int[][][][] sDrawableId = {
		{
			{
				{R.drawable.o1gf, R.drawable.o1gh, R.drawable.o1ge,},
				{R.drawable.o1rf, R.drawable.o1rh, R.drawable.o1re,},
				{R.drawable.o1pf, R.drawable.o1ph, R.drawable.o1pe,},
			},
			{
				{R.drawable.o2gf, R.drawable.o2gh, R.drawable.o2ge,},
				{R.drawable.o2rf, R.drawable.o2rh, R.drawable.o2re,},
				{R.drawable.o2pf, R.drawable.o2ph, R.drawable.o2pe,},
			},
			{
				{R.drawable.o3gf, R.drawable.o3gh, R.drawable.o3ge,},
				{R.drawable.o3rf, R.drawable.o3rh, R.drawable.o3re,},
				{R.drawable.o3pf, R.drawable.o3ph, R.drawable.o3pe,},
			},
		},
		{
			{
				{R.drawable.d1gf, R.drawable.d1gh, R.drawable.d1ge,},
				{R.drawable.d1rf, R.drawable.d1rh, R.drawable.d1re,},
				{R.drawable.d1pf, R.drawable.d1ph, R.drawable.d1pe,},
			},
			{
				{R.drawable.d2gf, R.drawable.d2gh, R.drawable.d2ge,},
				{R.drawable.d2rf, R.drawable.d2rh, R.drawable.d2re,},
				{R.drawable.d2pf, R.drawable.d2ph, R.drawable.d2pe,},
			},
			{
				{R.drawable.d3gf, R.drawable.d3gh, R.drawable.d3ge,},
				{R.drawable.d3rf, R.drawable.d3rh, R.drawable.d3re,},
				{R.drawable.d3pf, R.drawable.d3ph, R.drawable.d3pe,},
			},
		},
		{
			{
				{R.drawable.s1gf, R.drawable.s1gh, R.drawable.s1ge,},
				{R.drawable.s1rf, R.drawable.s1rh, R.drawable.s1re,},
				{R.drawable.s1pf, R.drawable.s1ph, R.drawable.s1pe,},
			},
			{
				{R.drawable.s2gf, R.drawable.s2gh, R.drawable.s2ge,},
				{R.drawable.s2rf, R.drawable.s2rh, R.drawable.s2re,},
				{R.drawable.s2pf, R.drawable.s2ph, R.drawable.s2pe,},
			},
			{
				{R.drawable.s3gf, R.drawable.s3gh, R.drawable.s3ge,},
				{R.drawable.s3rf, R.drawable.s3rh, R.drawable.s3re,},
				{R.drawable.s3pf, R.drawable.s3ph, R.drawable.s3pe,},
			},
		},
	};

	public static enum Shape {
		OVAL, DIAMOND, SQUIGGLE
	}

	public static enum Count {
		ONE, TWO, THREE
    }
	
	public static enum Color {
		GREEN, RED, PURPLE
	}
	
	public static enum Fill {
		FULL, HALF, EMPTY
	}

    public static final Card BLANK_CARD = new Card(null, null, null, null);

	private final Shape mShape;
	private final Count mCount;
	private final Color mColor;
	private final Fill mFill;
	
	public Card(Shape shape, Count count, Color color, Fill fill) {
		mShape = shape;
		mCount = count;
		mColor = color;
		mFill = fill;
	}
	
	public boolean isBlank() {
        return ((mShape == null) || (mColor == null) || (mCount == null) || (mFill == null));
	}
	
	public int getDrawableId() {
		if (isBlank()) {
			return R.drawable.blank;
		}
		return sDrawableId[mShape.ordinal()][mCount.ordinal()][mColor.ordinal()][mFill.ordinal()];
	}
	
    @Override
    public int hashCode() {
        if (isBlank()) {
            return 1;
        }
        int result = mShape.hashCode();
        result = 31 * result + mCount.hashCode();
        result = 31 * result + mColor.hashCode();
        result = 31 * result + mFill.hashCode();
        return result;
    }

    @Override
	public boolean equals(Object o) {
		if (!(o instanceof Card)) {
			return false;
		}
		Card other = (Card) o;
		return (mShape == other.mShape) &&
				(mCount == other.mCount) &&
				(mColor == other.mColor) &&
				(mFill == other.mFill);
	}
	
	public Card getCompleter(Card c) {
		if (isBlank() || c.isBlank()) {
			return BLANK_CARD;
		}
		Shape shape = null;
		Count count = null;
		Color color = null;
		Fill fill = null;
		if (mShape == c.mShape) {
			shape = mShape;
		} else {
			for (Shape s : Shape.values()) {
				if ((s != mShape) && (s != c.mShape)) {
					shape = s;
					break;
				}
			}
		}
		if (mCount == c.mCount){
			count = mCount;
		} else {
			for (Count cc : Count.values()) {
				if ((cc != mCount) && (cc != c.mCount)) {
					count = cc;
					break;
				}
			}
		}
		if (mColor == c.mColor) {
			color = mColor;
		} else {
			for (Color cc : Color.values()) {
				if ((cc != mColor) && (cc != c.mColor)) {
					color = cc;
					break;
				}
			}
		}
		if (mFill == c.mFill) {
			fill = mFill;
		} else {
			for (Fill f : Fill.values()) {
				if ((f != mFill) && (f != c.mFill)) {
					fill = f;
					break;
				}
			}
		}
		return new Card(shape, count, color, fill);
	}
	
	@Override
	public String toString() {
		if (isBlank()) {
			return "Blank";
		}
		StringBuilder sb = new StringBuilder();
		switch(mShape) {
		case OVAL:
			sb.append("o");
			break;
		case DIAMOND:
			sb.append("d");
			break;
		case SQUIGGLE:
			sb.append("s");
			break;
		}
		switch(mCount) {
		case ONE:
			sb.append("1");
			break;
		case TWO:
			sb.append("2");
			break;
		case THREE:
			sb.append("3");
			break;
		}
		switch(mColor) {
		case GREEN:
			sb.append("g");
			break;
		case RED:
			sb.append("r");
			break;
		case PURPLE:
			sb.append("p");
			break;
		}
		switch(mFill) {
		case FULL:
			sb.append("f");
			break;
		case HALF:
			sb.append("h");
			break;
		case EMPTY:
			sb.append("e");
			break;
		}
		return sb.toString();
	}
}
