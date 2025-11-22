@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String preferences; // JSON formatında tercihler

    @OneToMany(mappedBy = "user")
    private List<Trip> trips;

    // Getters, Setters, Constructors
}