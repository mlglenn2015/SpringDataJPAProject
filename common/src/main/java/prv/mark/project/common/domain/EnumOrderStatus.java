package prv.mark.project.common.domain;

/**
 * Enumeration for the Order statuses.
 *
 * @author mlglenn on 11/26/2016.
 */
public enum EnumOrderStatus {

    PENDING(1, "PENDING"),
    COMPLETED(2, "COMPLETED"),
    CANCELLED(3, "CANCELLED");

    private Integer statusId;
    private String statusDesc;

    EnumOrderStatus(Integer statusId, String statusDesc) {
        this.statusId = statusId;
        this.statusDesc = statusDesc;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
}
