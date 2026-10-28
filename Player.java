package TBVG;

public class Player
{
    private int health, numKeys;
    private Room currentRoom;

    public Player(Room r)
    {
        health = 10;
        numKeys = 1;
        currentRoom = r;
    }

    public void addKeys(int keys)
    {
        numKeys += keys;
    }

    public boolean hasKey()
    {
        if (numKeys > 0)
            return true;
        else
            return false;
    }

    public Room getRoom()
    {
        return currentRoom;
    }

    public int getKeys()
    {
        return numKeys;
    }

    public int getHealth()
    {
        return health;
    }

    public void takeDamage(int damage)
    {
        health -= damage;
    }

    public void attack(Enemy e)
    {
        int damageDealt = (int) (Math.random()*5)+1;
        e.takeDamage(damageDealt);
    }

    void changeRooms(Room r)
    {
        currentRoom = r;
    }

}