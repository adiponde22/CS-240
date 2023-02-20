#include <stdio.h>
#include <stdlib.h>

void primeChecker(int n);

int main(void)
{
    int userInput;

    printf("Enter a number: ");
    scanf("%d", &userInput);

    primeChecker(userInput);
}

void primeChecker(int n)
{

    if (n == 2 || n == 3)
    {
        printf("%d Is a Prime\n", n);
        exit(EXIT_SUCCESS);
    }

    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            printf("%d Is a NOT Prime\n", n);
            exit(EXIT_SUCCESS);
        }
    }

    printf("%d Is a Prime\n", n);
    exit(EXIT_SUCCESS);
}