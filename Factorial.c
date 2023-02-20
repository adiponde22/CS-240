#include <stdio.h>

// declaring function so compiled knows it exists
void numberFactorial(int userIn);

int main(void)
{      
    // Integer that stores user input
    int userInput = 0;

    // Scanning user input
    printf("Enter the number you want a factorial of: ");
    scanf("%d", &userInput);
    
    // function call that calculates the factorial and prints it
    numberFactorial(userInput);
    
    // end of program
    return 0;
}

// function call that calculates the factorial and prints it
void numberFactorial(int userIn)
{   
    // factorial
    long long int fact = 1;

    // checking for negative numbers
    if(userIn < 0)
    {
        printf("\nNegative number!");
    } 

    else 
    {   
        // for loop to calculate factorial
        for(int i = 1; i <= userIn; i++)
        {
            fact *= i;
        }
        
        //print factorial once calculated
        printf("\nFactorial of %d is: %lld\n", userIn, fact);
    }

}