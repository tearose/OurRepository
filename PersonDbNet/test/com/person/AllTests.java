package com.person;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DsCSVTest.class, PersonListTest.class, PersonTest.class })
public class AllTests
{

}
