# satjug-batch-demo
Java SE Batch (JSR-352) Presentation for San Antonio Java Users Group (JUG)

### Running this locally
* In order to run this locally, you'll need to either comment line 21 or 22 in `Main.java`
* This will either the `sampleBatch` (reading/writing to/from file) or `dataCorrectionBatch` (reading/writing to/from database)
* You will also need to download SQL Server Management Studio - https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver15
* I've included the 'create' scripts in `createScripts.sql`
* The data needed is included in `dataCorrectionPrep.sql`
* The command to run this job is `gradlew clean build run` (or /gradlew if running in a terminal outside of IntelliJ).