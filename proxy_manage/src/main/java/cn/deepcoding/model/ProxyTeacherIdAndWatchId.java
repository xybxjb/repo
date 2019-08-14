package cn.deepcoding.model;

/**
 * 招生老师微信与数据库的关系映射表
 */
public class ProxyTeacherIdAndWatchId {
	private Integer id;//主键ID
	private ProxyTeacher proxyteacheer;//数据库招生老师的ID
	private ProxyTeacherWatch proteacherwatch;//招生老师的微信id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProxyTeacher getProxyteacheer() {
		return proxyteacheer;
	}

	public void setProxyteacheer(ProxyTeacher proxyteacheer) {
		this.proxyteacheer = proxyteacheer;
	}

	public ProxyTeacherWatch getProteacherwatch() {
		return proteacherwatch;
	}

	public void setProteacherwatch(ProxyTeacherWatch proteacherwatch) {
		this.proteacherwatch = proteacherwatch;
	}

	@Override
	public String toString() {
		return "ProxyTeacherIdAndWatchId [id=" + id + ", proxyteacheer=" + proxyteacheer + ", proteacherwatch="
				+ proteacherwatch + "]";
	}

}
