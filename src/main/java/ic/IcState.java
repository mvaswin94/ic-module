package mipl.icmodule.ic;

import lombok.Getter;

@Getter
public enum IcState {
	PREPARED,ALLOCATED,NOT_ALLOCATED,APPROVED,NOT_APPROVED,DISPATCHED,DELIVERED,RETURNED
}
