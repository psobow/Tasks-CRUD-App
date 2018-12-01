set PASSWORD=witam

call mysql -u root -p%PASSWORD% < createDatabase.sql
