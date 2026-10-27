package TBVG;

public class Room {
    private String description;
    private int keys;
    private boolean isSearched, hasTreasure, isLocked, hasEnemy;
    private Enemy enemy;
    private Room northRoom, southRoom, westRoom, eastRoom;

    // null room
    public Room()
    {
        description = "This room does not exist";
    }

    // constructor
    public Room(String description, boolean hasTreasure, boolean hasEnemy)
    {
        this.description = description;
        isSearched = false;
        isLocked = true;
        this.hasTreasure = hasTreasure;
        this.hasEnemy = hasEnemy;
        keys = (int) (Math.random()*3);
    }

    // setEnemy
    public void setEnemy(Enemy e)
    {
        this.enemy = e;
    }

    public Enemy getEnemy()
    {
        return enemy;
    }

    // set neighbors
    public void setNeighbors(Room n, Room s, Room e, Room w)
    {
        northRoom = n;
        southRoom = s;
        eastRoom = e;
        westRoom = w;
    }

    public int search()
    {
        if (!isSearched)
        {
            this.isSearched = true;
            System.out.println("You found "+ keys + " new keys");
            return keys;
        }
        else
        {
            System.out.println("This room has already been searched.");
            return 0;
        }
    }

    public void enterRoom(Player p)
    {
        if (isLocked)
        {
            if (p.hasKey())
            {
                System.out.println("You unlock the door and enter a new room.");
                p.changeRooms(this);

            }
            else
                System.out.println("You need a key");
        }
        else 
            p.changeRooms(this);
    }

    public String getDescription()
    {
        return description;
    }

    public Room getNeighbor(String direction)
    {
        if (direction.equalsIgnoreCase("north"))
        {
            return northRoom;
        }
        else if (direction.equalsIgnoreCase("south"))
        {
            return southRoom;
        }
        else if (direction.equalsIgnoreCase("east"))
        {
            return eastRoom;
        }
        else if (direction.equalsIgnoreCase("west"))
        {
            return westRoom;
        }
        else
        {
            return null;
        }
    }

    public boolean hasEnemy()
    {
        return hasEnemy;
    }
}
