# Snakes and Ladders

Attempts to find the minimum number of dice throws needed to complete a game of snakes and ladders

Uses Dijkstra's Shortest Path Algorithm.  Using example from http://renaud.waldura.com/doc/java/dijkstra/

## Current Issues

- Output values are offset by one from real board numbers
- Output considers moving up a ladder as a move, rather than being part of the move when you land on it
- Internal data structures need replacing
- Will not take a snake if it leads to a better ladder
- Tests do not use assertions, just sysouts

