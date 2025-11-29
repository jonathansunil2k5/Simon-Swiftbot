# Simon-Swiftbot
"Simon Says" on the Swiftbot. Uses Swiftbot API

## String system
This program uses a string system to represent both generated button sequences and input sequences, e.g: YGR would light up Yellow-Green-Red
Since the labels on the swiftbot buttons (A,B,X,Y) don't ALL correspond to a color, they are converted to (R,B,G,Y) respectively.
Both sequences are stored as this converted string as opposed to using the button names for easier debugging.

In the program, this string is processed to light up the randomly generated button sequences and input sequences as they are input, the latter for user feedback.

# Main loop overview
The main game loop is as follows:
1. Append buttonSequence with a random colour
2. Get userInput. Loses if presses a button that doesn't match the correct element in the sequence at any point.
    -E.g: If buttonSequence is RGB, and user enters Y, the program will consider the run lost as soon as Y is entered.
3. If won, check if level is a multiple of 5.
       -If is then ask to continue, if not then just continue.
   If lost, ask to retry.
4. If at any point user doesn't want to continue, end the loop.

# Bot movement
Roughly speaking, the relationship between swiftbot units and speed (on a rough, carpet-like surface) can be modelled using:
$y=-0.35x^2+0.5x+30$, 
where y is speed in cm/s, and x is swiftbot units. The derivation for this equation can be found on the repository.

In this equation, x = 0, is 100 swiftbot units, and x = 10, is 0 swiftbot units, so we can use f(x) = 10x+100 to convert from swiftbot units into x for the equation.

Generally speaking, the swiftbot tends to travel 30cm/s at 100 swiftbot units or x=0.
In order to make the swiftbot move ~30cm every time, we need to get a speed from the above equation by inputting the correct value for x, then using that speed to calculate time travelled.
