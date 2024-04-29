public class Person
{
    private int age;
    private char gender;
    private String name;
    private String homeAddress;
    private int phoneNumber;

    public Person(String initName, char initGender, int initAge,
                  String initHomeAddress, int initPhoneNumber)
    {
        setName(initName);
        setGender(initGender);
        setAge(initAge);
        setHomeAddress(initHomeAddress);
        setPhoneNumber(initPhoneNumber);
    }



    public int getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber)
    {
        if(phoneNumber > 0)
            this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress()
    {
        return homeAddress;
    }
    public void setHomeAddress(String homeAddress)
    {
        if(!homeAddress.isEmpty())
            this.homeAddress = homeAddress;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        if(age >= 0)
            this.age = age;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        if(!name.isEmpty())
            this.name = name;
    }
    public char getGender()
    {
        return gender;
    }

    public void setGender(char gender)
    {
        if(gender == 'm' || gender == 'M' ||
                gender == 'f' || gender == 'F')
            this.gender = gender;

    }

    public String toString()
    {
        String info = "";

        info += "\n ACCOUNT HOLDER:" + "\n NAME: " + getName() + "\n Gender: ";
        if(getGender() == 'm' || getGender() == 'M')
            info += "male";
        else if(getGender() == 'f' || getGender() == 'F')
            info += "female";

        info += "\n Age: " + getAge() + "\n Phone Number: " + getPhoneNumber() + "\n Address: " + getHomeAddress();

        return info;
    }
}
