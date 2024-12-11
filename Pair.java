package helpers;

import java.util.AbstractMap;

    public class Pair<A, B> extends AbstractMap.SimpleEntry<A, B>
    {
        /**
         * Default constructor
         * @param a The first item of the Pair
         * @param b The second item of the Pair
         */
        public Pair(A a, B b)
        {
            super(a, b);
        }
        
        /**
        * Gets the left element of the pair
        * @return the left element of the pair
        */
        public A getLeftEl()
        {
            return getKey();
        }
        
        /**
        * Gets the right element of the pair
        * @return the right element of the pair
        */
        public B getRightEl()
        {
            return getValue();
        }
        
        @Override
        public String toString()
        {
            return "Pair<" + getKey() + ", " + getValue() + ">";
        }

        public static <A, B> Pair<A, B> of(A a, B b)
        {
            // TODO Auto-generated method stub
            return new Pair<>(a, b);
        }
    }