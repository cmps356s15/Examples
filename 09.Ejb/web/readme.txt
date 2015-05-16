ToDo before running this example:
 * Open the command line then go to C:\glassfish-4.1\bin (or the folder where glassfish is installed) then run the following:
 * asadmin create-jms-resource --restype javax.jms.Queue jms/OoredooQueue
 * asadmin create-jms-resource --restype javax.jms.QueueConnectionFactory jms/OoredooQueueConnectionFactory
 * Note asadmin is located @ ...glassfish-?-?\bin  (...your glassfish installation folder)

Run:
1) ejb.stateless.controller.Calculator
2) ejb.statefull.controller.Shopping
3) ejb.mdb.controller.OoredooTopup