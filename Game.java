package TBVG;

import java.util.Scanner;

public class Game 
{
    private static Room n,s,e,w,startingRoom,finalRoom;
    
    public static void setupGame()
    {
        // Making the map
        // Creating the rooms
        String startingRoomDescription = "The room is mostly empty with a door on each wall.";
        String finalRoomDescription = "The room shines full of treasure.";
        n = new Room("The room contains a few scattered gold coins on the ground. There is a room to the north.",false,true);
        s = new Room("There is a small stream that flows through this room. There is no new doors.",false,true);
        e = new Room("The room looks like an old prison. Skeletons are shackled to the walls. There are no new doors.",false,true);
        w = new Room("The room has a lot of moss. There are no new doors.",false,true);
        startingRoom = new Room(startingRoomDescription, false, false);
        finalRoom = new Room(finalRoomDescription, true, true);

        // Connecting rooms
        startingRoom.setNeighbors(n, s, e, w);
        n.setNeighbors(finalRoom, startingRoom, null, null);
        s.setNeighbors(startingRoom, null, null, null);
        e.setNeighbors(null, null, null, startingRoom);
        w.setNeighbors(null, null, startingRoom, null);
        finalRoom.setNeighbors(null, n, null, null);

        // Adding enemies into map
        Enemy nSkeleton = new Enemy(n);
        n.setEnemy(nSkeleton);
        Enemy sSkeleton = new Enemy(s);
        s.setEnemy(sSkeleton);
        Enemy eSkeleton = new Enemy(e);
        e.setEnemy(eSkeleton);
        Enemy wSkeleton = new Enemy(w);
        w.setEnemy(wSkeleton);
        Enemy fSkeleton = new Enemy(finalRoom);
        finalRoom.setEnemy(fSkeleton);

        // Welcome Statement
        System.out.println("Welcome to the dungeon.\nExplore the dungeon by searching each room for a key and unlocking the neighboring rooms.\nYou must find the treasure to win. If you perish along the way, you will lose.\nBeware of skeletons that lurk in the dark.");
    }

    public static void gameLoop()
    {
        Scanner input = new Scanner(System.in);
        boolean gameOver = false;
        Player p = new Player(startingRoom);

        while (!gameOver)
        {
            // TO DO #1 Display the current room.
            Room room = p.getRoom();
            System.out.println(room.getDescription());

            // TO DO #2 Check for enemy and add combat
            if (room.hasEnemy())
            {
                Enemy enemy = room.getEnemy();

                System.out.println("Suddenly, " + enemy.getDescription() + " appears!");
                System.out.println("You must defeat it! Type attack to fight!");

                while (enemy.isAlive() && p.getHealth() > 0)
                {
                    String attackCommand = input.nextLine();

                    if (attackCommand.equalsIgnoreCase("attack"))
                    {
                        // Player attacks
                        p.attack(enemy);
                        System.out.println("You attack " + enemy.getDescription() + ".");

                        // Check whether the enemy was defeated
                        if (!enemy.isAlive())
                        {
                            System.out.println("You defeated " + enemy.getDescription() + "!");
                            room.setEnemy(null);
                            break;
                        }

                        // Enemy attacks if it survives
                        enemy.attack(p);
                        System.out.println("The enemy attacks you!");
                        System.out.println("Your health: " + p.getHealth());
                        System.out.println("You need to attack again!");

                        // Check whether the player was defeated
                        if (p.getHealth() <= 0)
                        {
                            System.out.println("You have been defeated!");
                            gameOver = true;
                            break;
                        }
                    }
                    else
                    {
                        System.out.println("You coward! Type attack to fight!");
                    }
                }
            }

            // TO DO #3 Ask the player for a command
            System.out.println("What would you like to do?");
            System.out.println("Commands: north, south, east, west, search, inventory, quit");

            // TO DO #4 Process the command and handle invalid commands
            String command = input.nextLine();
            Room nextRoom;
            if (command.equalsIgnoreCase("north"))
            {
                nextRoom = room.getNeighbor("north");
                if (nextRoom != null)
                {
                    nextRoom.enterRoom(p);
                }
                else
                {
                    System.out.println("You cannot move north.");
                }
            }
            else if (command.equalsIgnoreCase("south"))
            {
                nextRoom = room.getNeighbor("south");
                if (nextRoom != null)
                {
                    nextRoom.enterRoom(p);
                }
                else
                {
                    System.out.println("You cannot move south.");
                }
            }
            else if (command.equalsIgnoreCase("west"))
            {
                nextRoom = room.getNeighbor("west");
                if (nextRoom != null)
                {
                    nextRoom.enterRoom(p);
                }
                else
                {
                    System.out.println("You cannot move west.");
                }
            }
            else if (command.equalsIgnoreCase("east"))
            {
                nextRoom = room.getNeighbor("east");
                if (nextRoom != null)
                {
                    nextRoom.enterRoom(p);
                }
                else
                {
                    System.out.println("You cannot move east.");
                }
            }
            else if (command.equalsIgnoreCase("search"))
            {
                p.addKeys(room.search());
            }
            else if (command.equalsIgnoreCase("inventory"))
            {
                System.out.println("You have "+p.getKeys()+" keys.");
            }
            else if (command.equalsIgnoreCase("quit"))
            {
                gameOver = true;
            }
            else
            {
                System.out.println("Invalid command. Please try again.");
            }

            // TO DO #5 Check whether the game has ended.
            if (p.getHealth() <= 0)
            {
                System.out.println("You have been defeated!");
                gameOver = true;
            }
            else if (p.getRoom() == finalRoom)
            {
                System.out.println("Congratulations! You found the treasure!");
                gameOver = true;
            }
        }
        input.close();
    }
}


