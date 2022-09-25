package pattern.refactor.nullControl.after;

public class Person {
    private Label name;
    private Label mail;

    public Person(Label name, Label mail) {
        this.name = name;
        this.mail = mail;
    }

    public Person(Label name) {
        this(name, null);
    }

    public void display(){
        if(!name.isNull()){
            name.display();
        }

        if(!mail.isNull()){
            mail.display();
        }
    }

    public String toString(){
        String result = "[ Person : ";

        result += " name =";
        if(name.isNull()){
            result += "\"(none)\"";
        }else {
            result += name;
        }

        result+= " mail =";
        if(mail.isNull()){
            result += "\"(none)\"";
        }else {
            result += mail;
        }

        result +=" ]";

        return result;
    }

}
