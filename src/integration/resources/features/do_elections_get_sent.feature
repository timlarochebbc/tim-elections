Feature: Do election results get sent to the database properly?
  Election results need to be logged and displayed properly.

  Scenario: Election results get sent properly
    Given our POST /newresult endpoint is hit with valid XML data and the database is empty
    When we hit our GET /labresults endpoint
    Then we should get some information