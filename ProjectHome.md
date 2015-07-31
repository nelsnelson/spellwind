# Introduction #

Spellwind is a MUD (multi-user dungeon) server written in Java. Unlike the other Java MUD servers, we are aiming to exploit many of the newer features of the language and work with next-generation technologies.

# Technical Overview #

The networking code uses the JBoss Netty API for high performance and abstraction from low-level NIO operations.

The rooms, areas, mobs, items, etc. in the MUD are all stored in a YAML-based format, meaning they should be easy to parse and also allow builders/content developers to easily create new content without worrying about databases, SQL, relationships, tables and other more advanced topics. The JYaml library is used for this.

Game logic itself is based around a publish/subscribe system. Objects in the MUD can 'publish' messages which the subscribers will receive. Messages are also converted to text, making it easy for players to receive messages from the MUD.

# Current Information #

The project is currently in the early development/planning stages. It is released under the New BSD License.

# Statistics #

Find more on the [ohloh](https://www.ohloh.net/p/spellwind) page.

&lt;wiki:gadget url="http://www.ohloh.net/p/249170/widgets/project\_factoids.xml" border="1"/&gt;&lt;wiki:gadget url="http://www.ohloh.net/p/249170/widgets/project\_languages.xml" border="1"/&gt;&lt;wiki:gadget url="http://www.ohloh.net/p/249170/widgets/project\_basic\_stats.xml" height="220" border="1"/&gt;