package TBVG;

public class Enemy {
    private int health;
    private Room currentRoom;
    private String description;

    public Enemy(Room r)
    {
        currentRoom = r;
        health = 5;
        description = "a spooky, scary skeleton";
    }

    public void takeDamage(int damage)
    {
        health -= damage;
    }

    public boolean isAlive()
    {
        if (health > 5)
        {
            return true;
        }
        else 
            return false;
    }

    public void attack(Player p)
    {
        int damageDealt = (int) (Math.random()*3)+1;
        p.takeDamage(damageDealt);
    }

    public String getDescription()
    {
        return description;
    }
}
