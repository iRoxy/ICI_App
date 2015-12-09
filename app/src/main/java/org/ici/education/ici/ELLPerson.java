package org.ici.education.ici;

import java.util.Date;

/**
 * Created by al2king on 12/8/2015.
 */
public class ELLPerson
{
    private String fName, mName, lName, email, username, password, group, language, notes, county;
    private int bdayMonth, bdayDate;
    private Date birthday;


    public String getfName()
    {
        return fName;
    }

    public String getmName()
    {
        return mName;
    }

    public String getlName()
    {
        return lName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getGroup()
    {
        return group;
    }

    public String getLanguageLevel()
    {
        return language;
    }

    public String getNotes()
    {
        return notes;
    }

    public String getCounty()
    {
        return county;
    }

    public int getBdayMonth()
    {
        return bdayMonth;
    }

    public int getBdayDate()
    {
        return bdayDate;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public ELLPerson(String _fName, String _mName, String _lName, String _email, Date _birthday, String _language, String _notes, String _county)
    {
        fName = _fName;
        mName = _mName;
        lName = _lName;
        email = _email;
        birthday = _birthday;
        language = _language;
        notes = _notes;
        county = _county;
        password = "eslstudents";
        username = "";
        genUsername();
    }

    private void genUsername()
    {
        bdayMonth = birthday.getMonth() + 1;
        bdayDate = birthday.getDate();
        username += lName;
        username += (bdayMonth < 10)? "0" + bdayMonth : bdayMonth;
        username += (bdayDate < 10)? "0" + bdayDate : bdayDate;
    }


}
