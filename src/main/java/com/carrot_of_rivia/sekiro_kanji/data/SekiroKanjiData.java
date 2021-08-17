package com.carrot_of_rivia.sekiro_kanji.data;

import com.carrot_of_rivia.sekiro_kanji.utils.ConfigCommon;

public class SekiroKanjiData {

    private int dangerCD = 0;
    private int resurrectCD = 0;
    private int poisonCD = 0;
    private int burnCD = 0;
    private int enemyAlertCD = 0;


    public SekiroKanjiData(){

    }

    public int getDangerCD() {
        return dangerCD;
    }

    public int getResurrectCD() {
        return resurrectCD;
    }

    public int getPoisonCD() {
        return poisonCD;
    }

    public int getBurnCD() {
        return burnCD;
    }

    public int getEnemyAlertCD() {
        return enemyAlertCD;
    }

    public void setDangerMax(){
        this.dangerCD = ConfigCommon.DANGER_CD.get();
    }
    public void setResurrectMax(){
        this.resurrectCD = ConfigCommon.RESURRECT_CD.get();
    }
    public void setBurnMax(){
        this.burnCD = ConfigCommon.BURN_CD.get();
    }
    public void setPoisonMax(){
        this.poisonCD = ConfigCommon.POISON_CD.get();
    }
    public void setEnemyAlertMax(){
        this.enemyAlertCD = ConfigCommon.ENEMY_ALERT_CD.get();
    }

    public void tickCD(){
        if(this.dangerCD > 0){
            dangerCD -= 1;
        }
        if(this.resurrectCD > 0){
            resurrectCD -= 1;
        }
        if(this.burnCD > 0){
            burnCD -= 1;
        }
        if(this.poisonCD > 0){
            poisonCD -= 1;
        }
        if(this.enemyAlertCD > 0){
            enemyAlertCD -= 1;
        }
    }

    public boolean canSendDangerSign(){
        return dangerCD <= 0;
    }
    public boolean canSendPoisonSign(){
        return poisonCD <= 0;
    }
    public boolean canSendBurnSign(){
        return burnCD <= 0;
    }
    public boolean canSendResurrectSign(){
        return resurrectCD <= 0;
    }
    public boolean canSendEnemyAlertSign(){
        return enemyAlertCD <= 0;
    }

    public void setDangerCD(int dangerCD) {
        this.dangerCD = dangerCD;
    }

    public void setResurrectCD(int resurrectCD) {
        this.resurrectCD = resurrectCD;
    }

    public void setPoisonCD(int poisonCD) {
        this.poisonCD = poisonCD;
    }

    public void setBurnCD(int burnCD) {
        this.burnCD = burnCD;
    }

    public void setEnemyAlertCD(int enemyAlertCD) {
        this.enemyAlertCD = enemyAlertCD;
    }
}
