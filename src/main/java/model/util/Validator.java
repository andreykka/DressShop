package model.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gandy on 18.11.14.
 */
public class Validator {

    public static final String NAME_PATTERN         = "^[A-Za-zа-яА-Я]{2,40}$";
    public static final String PHOME_NOMBER_PATTERN = "\\+380(39|50|63|66|67|68|91|92|93|94|95|96|97|98|99)[0-9]{7}";
    public static final String ID_PATTERN           = "^[A-Za-z0-9]{5,10}$"; // identifier of  goods
    public static final String DECIMAL_PATTERN      = "^[1-9]{1}[0-9]{0,9}$";
    public static final String PRICE_PATTERN        = "^$"; // 0.10

    private Pattern namePattert;
    private Pattern phoneNublerPattern;
    private Pattern idPattert;
    private Pattern decimalPattern;
    private Pattern pricePattert;


    public Validator(){
        namePattert         = Pattern.compile(NAME_PATTERN);
        phoneNublerPattern  = Pattern.compile(PHOME_NOMBER_PATTERN);
        idPattert           = Pattern.compile(ID_PATTERN);
        decimalPattern = Pattern.compile(DECIMAL_PATTERN);
        pricePattert        = Pattern.compile(PRICE_PATTERN);
    }

    private boolean valid (String regex, final Pattern PATTERN) {
        Matcher matcher = PATTERN.matcher(regex);
        return matcher.matches();

    }

    public boolean validName(String name) {
        return this.valid(name, namePattert);
    }

    public boolean validPhoneNumber(String phoneNumber) {
        return this.valid(phoneNumber, phoneNublerPattern);
    }

    public boolean validId(String id) {
        return this.valid(id, idPattert);
    }

    public boolean validDecimal(String decimal) {
        return this.valid(decimal, decimalPattern);
    }

}
