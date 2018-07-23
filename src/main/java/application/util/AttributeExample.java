package application.util;

public enum AttributeExample {
    ATRRIBUTE_0("email","info@rol.ru"),
    ATRRIBUTE_1("surname","Pupkin"),
    ATTRIBUTE_2("name","Vasya"),
    ATRRIBUTE_3("fio","Ino.I.I"),
    ATRRIBUTE_4("email","mail@mail.com"),
    ATRRIBUTE_5("surname","PupkinJunior"),
    ATRRIBUTE_6("name","notVasya"),
    ATRRIBUTE_7("fio","Modruk K.K."),
    ATRRIBUTE_8("email","someMail@mail.com"),
    ATRRIBUTE_9("surname","Mayuri"),
    ATRRIBUTE_10("name","Kuritchi"),
    ATRRIBUTE_11("fio","Koryndant P.P."),
    ATRRIBUTE_12("email","happyemail@mail.com"),
    ATRRIBUTE_13("surname","Feynman"),
    ATRRIBUTE_14("name","Richard"),
    ATRRIBUTE_15("fio","Feynman R. P.");

    public final String name;
    public final String value;

    AttributeExample(String name,String value){
        this.name=name;
        this.value=value;
    }
}
