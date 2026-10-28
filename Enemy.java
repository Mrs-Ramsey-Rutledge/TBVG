package TBVG;

public class Enemy {
    private int health;
    private String description;

    public Enemy()
    {
        health = 5;
        description = "a spooky, scary skeleton";
    }

    public void takeDamage(int damage)
    {
        health -= damage;
    }

    public boolean isAlive()
    {
        return (health > 0);
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
