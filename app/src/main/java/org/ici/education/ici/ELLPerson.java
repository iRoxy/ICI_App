package org.ici.education.ici;

import java.util.Date;

/**
 * Created by al2king on 12/8/2015.
 */
public class ELLPerson
{
    private String fName, mName, lName, email, username, password, group, language, notes, county;
    private Date birthday;


    public ELLPerson(String _fName, String _mName, String _lName, String _email, Date birthday, String _language, String _notes, String county)
    {
        fName = _fName;
        mName = _mName;
        lName = _lName;

    }
}
