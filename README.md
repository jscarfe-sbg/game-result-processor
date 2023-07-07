# Game Result Processor (GRP)

The GRP (we love a 3 letter acronym at Skybet!) is a simple application which does the following:
1. Provides a HTTP endpoint to allow you to register a game result
2. Stores the game results 
3. Performs aggregations on the game results
4. Provides a couple of HTTP endpoints:
    1. One to retrieve the game stats
    2. One to retrieve the player stats

## 1. Running the application

### Installing Docker

The app is packaged as a docker image, meaning we can run it in any envrionemnt that has docker installed.

See https://docs.docker.com/get-docker/ to install for your operating system.

### Building and running the application

First you need to build:
`docker build . -t game-result-processor`

Then you can run:
`docker run -p 8080:8080 -it game-result-processor:latest`

## 2. Interface

### Registering a game result

Address: http://0.0.0.0:8080/result
Method: POST

Example request:
```
{
    "id": "1",
    "user": "James",
    "game": "Blackjack",
    "win": false,
    "stake": "3.50",
    "payout": "0.00",
    "timestamp": "1688735147"
}
```

### Retrieving game stats

Addresses: 
- http://0.0.0.0:8080/game-stats
- http://0.0.0.0:8080/game-stats/{game} (e.g. http://0.0.0.0:8080/game-stats/Blackjack)
Method: GET

#### Example responses:
All games
```
{
    "Roulette": {
        "game": "Roulette",
        "totalStaked": 5.0,
        "totalPayout": 4.0,
        "totalWins": 2,
        "totalLosses": 0
    },
    "Blackjack": {
        "game": "Blackjack",
        "totalStaked": 10.5,
        "totalPayout": 0.0,
        "totalWins": 0,
        "totalLosses": 3
    }
}
```

With a game specified
```
{
    "game": "Roulette",
    "totalStaked": 5.0,
    "totalPayout": 4.0,
    "totalWins": 2,
    "totalLosses": 0
}
```

### Retrieving player stats

Addresses:
- http://0.0.0.0:8080/player-stats
- http://0.0.0.0:8080/player-stats/{player} (e.g. http://0.0.0.0:8080/player-stats/James)
Method: GET

#### Example responses:

All players 
```
{
    "James": {
        "user": "James",
        "totalStaked": 15.5,
        "totalPayout": 4.0,
        "totalWins": 2,
        "totalLosses": 3,
        "favouriteGame": "Blackjack"
    },
    "Bob": {
        "user": "Bob",
        "totalStaked": 5.0,
        "totalPayout": 4.0,
        "totalWins": 2,
        "totalLosses": 0,
        "favouriteGame": "Roulette"
    }
}
```

With a player specified
```
{
    "user": "James",
    "totalStaked": 15.5,
    "totalPayout": 4.0,
    "totalWins": 2,
    "totalLosses": 3,
    "favouriteGame": "Blackjack"
}
```
