/**
 * 
 */
package gs.tnt.dev.minecraft.data;

/**
 * @author ted
 *
 */
public class InvalidStateException extends Exception {

	private static final long serialVersionUID = 5253125526529971170L;
	public String error;

	public InvalidStateException() {

		super();
		error = "unknown";
	}

	public InvalidStateException(String error) {

		super(error);
		this.error = error;
	}

	public String getError() {

		return error;
	}
}