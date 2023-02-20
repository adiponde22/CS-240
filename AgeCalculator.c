#include <stdio.h>
#include <time.h>

int main(void)
{
    time_t t = time(NULL);
    struct tm tm = *localtime(&t);


    // users name
    char username[20];

    // users DOB
    int dob_day;
    int dob_month;
    int dob_year;

    // getting user name
    printf("Name: ");
    scanf("%[^\n]%*c", username);

    // getting user DOB
    char temp;
    printf("Date of Birth (mm/dd/yyyy): ");
    scanf("%d%c%d%c%d", &dob_month, &temp ,&dob_day, &temp ,&dob_year);


    int age_years = tm.tm_year + 1900 - dob_year;
    int age_months =tm.tm_mon + 1 - dob_month;
    int age_days = tm.tm_mday - dob_day;

    int daysInMonth[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if (age_days < 0) {
        age_months--;
        age_days += daysInMonth[tm.tm_mon]; // assume 30 days per month
    }
    if (age_months < 0) {
        age_years--;
        age_months += 12;
    }


    printf("Hello, %s! You are %d years, %d months, and %d days old.\n",username, age_years, age_months, age_days);
    
}

