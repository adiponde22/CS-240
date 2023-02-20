#include <stdio.h>
#include <stdlib.h>

int calculate(int a, int b, char expression);

int main(void)
{
    int userin1, userin2;
    char expression;

    printf("Enter expression: ");
    scanf("%d%c%d", &userin1, &expression, &userin2);
    printf("Result: %d\n", calculate(userin1, userin2, expression));

    return 0;
}

int calculate(int a, int b, char expression)
{
    if (expression == '+')
    {
        return a + b;
    }
    else if (expression == '-')

    {
        return a - b;
    }
    else if (expression == '*')
    {
        return a * b;
    }

    else if (expression == '/')
    {
        return a / b;
    }
    
    else if (expression == '%')
    {
        return a % b;
    }
    else 
    {
        printf("Invalid expression\n");
        exit(EXIT_FAILURE);
    }
    
    
}