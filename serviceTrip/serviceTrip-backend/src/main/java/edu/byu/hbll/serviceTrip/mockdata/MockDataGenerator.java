package edu.byu.hbll.serviceTrip.mockdata;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("CheckStyle")
public class MockDataGenerator {
  final int CURRENT_YEAR = 2018;
  ArrayList<String> organizationNames = new ArrayList<>();
  ArrayList<String> eventNames = new ArrayList<>();
  ArrayList<String> descriptions = new ArrayList<>();
  ArrayList<String> tags = new ArrayList<>();
  ArrayList<String> locations = new ArrayList<>();

  Random random = new Random();

  public MockDataGenerator() {
    organizationNames.add("Hands for Humanity");
    organizationNames.add("Helping Children Everywhere");
    organizationNames.add("You and me helping them");
    organizationNames.add("Let's help everyone");

    eventNames.add("Help Event for Teenagers");
    eventNames.add("Writing letters to...");
    eventNames.add("Come and help those who need it!");
    eventNames.add("Family friendly event to help local children");

    descriptions.add(
        "Rwanda is a country of children with no children's hospital.  Join us to help build the first children's hospital in Rwanda.\n"
            +
            "\n" +
            "Rwanda is a rapidly developing country, with a population dominated by children. The mortality rate among these children has been reduced by half by delivering basic health care to all. But to take the next leap forward, more advanced levels of care must also become available. With only one pediatrician for every 68,000 children, and mothers and children still dying from preventable causes, the next leap forward in Rwandan health care requires more pediatricians and pediatric subspecialists as well as strong technological development and a dedicated place to provide the care. \n"
            +
            "\n" +
            "Nyanja Brodin is an advocate of vulnerable children and is committed to the development of her country of Rwanda.  Together with her husband Petter Brodin (a pediatrician and accomplished researcher) and her friend, Melina Patterson (an attorney), Nyanja formed Little Hills to work together with the Rwandan community to increase the quality of care for mothers and children in Rwanda.\n"
            +
            "\n" +
            "With your support, the Little Hills projects will improve the quality of health care for mothers and children in Rwanda.  Funds raised by Little Hills will go towards:\n"
            +
            "·         Costs associated with bringing volunteer physicians and nurses to Rwanda, including housing, transportation, and other related costs;\n"
            +
            "·         Scholarships for disadvantaged Rwandan women to attend medical school;\n" +
            "·         Costs related to planning the construction of the hospital; and\n" +
            "·         Any money raised in excess of the above costs will go towards the construction of the hospital.\n"
            +
            "\n" +
            "For more information, visit  www.littlehills.org.\n" +
            "Follow us on Facebook, Instagram, and Twitter at @littlehillsorg\n" +
            "\n" +
            "Little Hills is a nonprofit organization incorporated in California as Tiny Hills, Inc. (EIN 83-1085563) and its application for 501(c)(3) status is currently pending.\n"
            +
            "\n" +
            "Contributions or gifts to Tiny Hills, Inc. DBA Little Hills are not deductible as charitable contributions for Federal income tax purposes, until the IRS approves its application for nonprofit status.\n"
            +
            "+ Read More...");
    descriptions.add(
        "As Seen on Shark Tank. My siblings and I are beyond grateful for all of the support and love we have been given. We want to give back as our way of saying thank you. The FDNY has been nothing short of incredible in supporting our family. We have set up this GoFundMe Page to give 100% to the FDNY Foundation's Fired Up for a Cure to support their efforts. \n");
    descriptions.add(
        "In the early morning of October 25th, 2018, the Northern Mariana Islands of Saipan and Tinian were devastated by Category 5 Super Typhoon. Scholars preparing to apply for college have instead lost everything, saving lives by sheltering in bathrooms. Elders, who rely on electricity or refrigeration for medical needs, are at risk and will be so for weeks to come. \n"
            +
            "\n" +
            "Our power systems have been badly damaged, our hotels and tourist attractions are damaged, and we have many injured and traumatized. The economy of the CNMI, having recently recovered and grown, will face serious setbacks and our already needy people will suffer now with heat, hunger, and illness. ");
    descriptions.add(
        "Charlie Bean is turning 3!  Let’s help to show her that it’s important to make a difference in this world!  \n"
            +
            "Mama Bean has been so tired building up the BFF that a Charity Run this year is just too much....plus it turns out that the prices of the medals have nearly doubled!  Which....is one of the main reasons why this year’s Birthday Fundraiser is so important!");

    tags.add("familyFriendly");
    tags.add("lowBudget");
    tags.add("coldWeather");
    tags.add("teenagersWelcome");
    tags.add("nearNature");
    tags.add("vacation");
    tags.add("education");
    tags.add("health");
    tags.add("spanishSpeaking");
    tags.add("abroad");
    tags.add("housingIncluded");
    tags.add("nearResorts");
    tags.add("nearBeaches");
    tags.add("multicultural");
  }

  public String getRandomOrganizationName(){
    return organizationNames.get(random.nextInt(organizationNames.size() - 1));
  }

  public String getRandomEventName(){
    return eventNames.get(random.nextInt(organizationNames.size() - 1));
  }

  public String getRandomEventDescription(){
    return descriptions.get(random.nextInt(descriptions.size() - 1));
  }

  public String getRandomTag(){
    return tags.get(random.nextInt(tags.size() - 1));
  }

  /*
    Dates are generated in the next 5 years
   */
  public Date getRandomFutureDate(){
    int year = random.nextInt(4) + 2018;
    int month = random.nextInt(10);
    int day = random.nextInt(28);

    long dateInMilliseconds = getDayInMilliseconds(day) + getDayInMilliseconds(month) + getYearInMilliseconds(year);

    return new Date(dateInMilliseconds);
  }

  private long getDayInMilliseconds(int day){
    // 24 hours in a day, 60 minutes in an hour, 60000 milliseconds in minute
    return  day * 24 * 60 * 60;
  }

  private long getMonthInMilliseconds(int month){
    // 28 days in a month, 24 hours in a day, 60 minutes in an hour, 60000 milliseconds in minute
    return month * 28 * 24 * 60 * 60;
  }

  /*
    returns year + CURRENT_YEAR in milliseconds
   */
  private long getYearInMilliseconds(int year){
    // 12 months in a year, 28 days in a month, 24 hours in a day, 60 minutes in an hour, 60000 milliseconds in minute
    return year * 12 * 28 * 24 * 60 * 60000;
  }

  public int getRandomCost(){
    return random.nextInt(800) + 400;
  }

  public int getRandomNumberEnrolled(){
    return random.nextInt(20) + 20;
  }


}
