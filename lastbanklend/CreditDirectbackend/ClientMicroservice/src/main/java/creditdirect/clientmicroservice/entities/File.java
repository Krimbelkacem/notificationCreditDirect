

package creditdirect.clientmicroservice.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class File {

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    // Constructors, getters, and setters
}
