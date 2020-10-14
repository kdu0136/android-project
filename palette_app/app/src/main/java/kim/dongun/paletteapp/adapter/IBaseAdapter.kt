package kim.dongun.paletteapp.adapter

/**
 * 어댑터에서 데이터 관련 기본적으로 필요한 기능 인터페이스
 */
interface IBaseAdapter {
    /**
     * 어댑터 데이터에 다른 데이터 참조
     *
     * @param linkData 참조 할 데이터
     */
    fun linkData(linkData: Any)

    /**
     * 어댑터 데이터를 새로운 데이터로 업데이트
     *
     * @param updateData 업데이트 할 새로운 데이터
     */
    fun updateData(updateData: Any)

    /**
     * 기존 어댑터 데이터에 새로운 데이터 추가
     *
     * @param addData 추가 할 데이터
     */
    fun addData(addData: Any)

    /**
     * 기존 어댑터 데이터 지정 된 위치에 새로운 단일 데이터 추가
     *
     * @param position 추가 할 위치
     * @param addListData 추가 할 단일 데이터
     */
    fun addAtData(position: Int, addListData: Any)

    /**
     * 기존 어댑터 데이터 지정 된 위치에 있는 데이터 삭제
     *
     * @param position 삭제 할 위치
     */
    fun removeAtData(position: Int)

    /**
     * 어댑터 데이터 불러오는 중으로 변경
     *
     * @param loading 로딩 item 띄워주는 여부
     */
    fun dataLoading(loading: Boolean)
}
