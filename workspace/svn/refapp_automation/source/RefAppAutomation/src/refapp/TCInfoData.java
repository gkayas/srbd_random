package refapp;

import java.util.List;

import org.xmlbeam.annotation.XBRead;

public interface TCInfoData {

	interface TCs {
		@XBRead("@level")
		int getLevel();

		@XBRead("@name")
		String getName();

		@XBRead("@precondition")
		String getPrecondition();
	}

	@XBRead("menutree/TC")
	List<TCs>getAllTCInfo();

}
