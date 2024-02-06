===================== Players App =====================

Microservice which serves the contents of player.csv through REST API.
When Application starts, a service called InitDatabase will start to work.
This service is responsible to parse the csv file only once(when the App is up) and
Inserts the data from the CSV to the DB (postgres DB).

* The CSV file location should be inside resources directory and its name controlled by
  property = FILE_NAME .

===================== First API =====================

1. GET /api/players - returns a list of all players in csv.
   Request params:
   pageNumber - Integer number representing the request page number, Optional, default value = 1
   pageSize - Integer number representing the request page size, Optional, default value = 10
   sort - String representing the request sorting value, Optional, default value = "playerID"

Fetches all data about all players from DB and returns it as a json.

===================== Second API =====================

2. GET /api/players/{playerID} - returns a single player by ID.

fetches all data about specific playerID from DB and returns it as json.

===================== TODO =====================
If I had more time I would do:

* Local Cache

So when we need data about specific player(second API), first we will search in the cache, if
playerId found will return its data, if not we query the DB and first insert
the data to the cache(key:playerId) then return the response.

* Scheduled job

So this job will have 1 method with cron that run for example every 5 minutes
(configurable) and have only 1 purpose,To scan the csv file and detect if there is
any update (new row/ deleted rows ...) and updates the DB accordingly.

* More filtering Options

So the client would have more options to search for data.
Example: GET /api/players/pagesNumber=1&pageSize=50&nameLast=haddad
pagination & filter by nameLast containing ‘haddad’

* Validation on sort request param value

I would add validation on sort request param, for example,
through Enum that includes only valid values(CSV columns values)

* Logs

I would use logger, for example slf4j