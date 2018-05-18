package com.ywqln.yqdroid.presenter;

import com.ywqln.yqdroid.entity.resp.HerosDo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:Marvel Role Presenter.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/5/17
 */
public class MarvelRolePresenter {

    public List<HerosDo> getHeroList() {
        List<HerosDo> herosDos = new ArrayList<>();

        HerosDo ironMan = new HerosDo();
        ironMan.setDecent(HerosDo.DECENT);
        ironMan.setName("钢铁侠");
        ironMan.setPower("发达的科技智慧 & 钢铁战衣");
        ironMan.setProfilePhoto("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=9abc171249c2d562f608d7efd71090f3/a9d3fd1f4134970a13caa95d93cad1c8a7865d02.jpg");

        HerosDo thor = new HerosDo();
        thor.setDecent(HerosDo.DECENT);
        thor.setName("雷神");
        thor.setPower("雷神之力");
        thor.setProfilePhoto("http://img4.imgtn.bdimg.com/it/u=3465390198,68417764&fm=11&gp=0.jpg");


        HerosDo blackPanther = new HerosDo();
        blackPanther.setDecent(HerosDo.DECENT);
        blackPanther.setName("黑豹");
        blackPanther.setPower("高度发达的科技智慧 & 黑豹之力 & 黑豹战甲");
        blackPanther.setProfilePhoto("http://img2.imgtn.bdimg.com/it/u=1142499341,878678973&fm=27&gp=0.jpg");

        HerosDo captainAmerica = new HerosDo();
        captainAmerica.setDecent(HerosDo.DECENT);
        captainAmerica.setName("美国队长");
        captainAmerica.setPower("超级战士血清 & 埃德曼合金的盾牌");
        captainAmerica.setProfilePhoto("http://img1.imgtn.bdimg.com/it/u=1952800350,942140221&fm=27&gp=0.jpg");

        HerosDo blackWidow = new HerosDo();
        blackWidow.setDecent(HerosDo.DECENT);
        blackWidow.setName("黑寡妇");
        blackWidow.setPower("优秀的格斗技巧 & 性感的外表");
        blackWidow.setProfilePhoto("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3223352657,3414861841&fm=27&gp=0.jpg");

        HerosDo doctorStrange = new HerosDo();
        doctorStrange.setDecent(HerosDo.DECENT);
        doctorStrange.setName("奇异博士");
        doctorStrange.setPower("天赋异禀的魔法师悟性 & 时间原石");
        doctorStrange.setProfilePhoto("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2982500216,1347443975&fm=27&gp=0.jpg");

        HerosDo vision = new HerosDo();
        vision.setDecent(HerosDo.DECENT);
        vision.setName("幻视");
        vision.setPower("原石力量");
        vision.setProfilePhoto("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3967840037,3833572149&fm=27&gp=0.jpg");


        HerosDo scarletWitch = new HerosDo();
        scarletWitch.setDecent(HerosDo.DECENT);
        scarletWitch.setName("绯红女巫");
        scarletWitch.setPower("混沌魔法");
        scarletWitch.setProfilePhoto("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3797140546,3177999625&fm=27&gp=0.jpg");

        HerosDo hulk = new HerosDo();
        hulk.setDecent(HerosDo.DECENT);
        hulk.setName("浩克");
        hulk.setPower("伽马辐射 & 浩克砸");
        hulk.setProfilePhoto("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526617245546&di=830ef67535b38159f8ba200b1439b7df&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fwh%253D680%252C800%2Fsign%3D98065e775966d0167e4c962eaf1bf83f%2Ff2deb48f8c5494ee51c2d2a326f5e0fe98257eac.jpg");

        HerosDo thanos = new HerosDo();
        thanos.setDecent(HerosDo.VILLAIN);
        thanos.setName("灭霸");
        thanos.setPower("泰坦 & 无限手套");
        thanos.setProfilePhoto("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526634387100&di=ee7982c17fc33b975303e0e8c89de215&imgtype=0&src=http%3A%2F%2Fqimg.hxnews.com%2F2018%2F0329%2F1522291784816.jpg");

        herosDos.add(ironMan);
        herosDos.add(thor);
        herosDos.add(blackPanther);
        herosDos.add(captainAmerica);
        herosDos.add(blackWidow);
        herosDos.add(doctorStrange);
        herosDos.add(vision);
        herosDos.add(scarletWitch);
        herosDos.add(hulk);
        herosDos.add(thanos);

        return herosDos;
    }
}
