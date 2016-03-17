package cn.by.eform.ui.factory;

import static com.jgoodies.common.base.Preconditions.checkArgument;
import static com.jgoodies.common.base.Preconditions.checkNotNull;

import com.jgoodies.binding.value.BindingConverter;

public class StringToBooleanConverter implements BindingConverter {


    private final String trueText;
    private final String falseText;
    private final String nullText;

    public StringToBooleanConverter(
            String trueText,
            String falseText,
            String nullText) {
        this.trueText  = checkNotNull(trueText, "The trueText must not be null.");
        this.falseText = checkNotNull(falseText, "The falseText must not be null.");
        this.nullText  = checkNotNull(nullText, "The nullText must not be null.");
        checkArgument(!trueText.equals(falseText),
                "The trueText and falseText must be different.");
    }


    /**
     * Converts the subject value to associated text representation.
     * Rejects non-Boolean values.
     *
     * @param sourceValue the subject's new value
     * @return the text that represents the subject value
     *
     * @throws ClassCastException if the subject's value is not a Boolean
     */
    @Override
    public Object targetValue(Object sourceValue) {
    	 if (!(sourceValue instanceof String)) {
             throw new ClassCastException("The new value must be a string.");
         }

         String newString = (String) sourceValue;
         if (trueText.equalsIgnoreCase(newString)) {
             return Boolean.TRUE;
         } else if (falseText.equalsIgnoreCase(newString)) {
             return Boolean.FALSE;
         } else if (nullText.equalsIgnoreCase(newString)) {
             return null;
         } else {
             throw new IllegalArgumentException(
                     "The new value must be one of: "
                   + trueText + '/'
                   + falseText + '/'
                   + nullText);
         }
    }


    /**
     * Converts the given String and sets the associated Boolean as
     * the subject's new value. In case the new value equals neither
     * this class' trueText, nor the falseText, nor the nullText,
     * an IllegalArgumentException is thrown.
     *
     * @param targetValue  the value to be converted and set as new subject value
     * @throws ClassCastException if the new value is not a String
     * @throws IllegalArgumentException if the new value does neither match
     *     the trueText nor the falseText nor the nullText
     */
    @Override
	public Object sourceValue(Object targetValue) {
        
        if (Boolean.TRUE.equals(targetValue)) {
            return trueText;
        } else if (Boolean.FALSE.equals(targetValue)) {
            return falseText;
        } else if (targetValue == null) {
            return nullText;
        } else {
            throw new ClassCastException(
            "The subject value must be of type Boolean.");
        }
    }

}
