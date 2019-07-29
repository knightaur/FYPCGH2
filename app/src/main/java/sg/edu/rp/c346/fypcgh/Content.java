package sg.edu.rp.c346.fypcgh;

import android.os.Parcel;
import android.os.Parcelable;


public class Content implements Parcelable {

    public final String drug;
    public final String fast;
    public final String adm;
    public final String ni;
    public final String sc;
    public final String ac;
    public final String id;
    public final String queue;
    public final String nric;

    public Content(String drug, String fast, String adm, String ni, String sc, String ac, String id, String queue, String nric) {
        this.drug = drug;
        this.fast = fast;
        this.adm = adm;
        this.ni = ni;
        this.sc = sc;
        this.ac = ac;
        this.id = id;
        this.queue = queue;
        this.nric = nric;
    }

    protected Content(Parcel in) {
        drug = in.readString();
        fast = in.readString();
        adm = in.readString();
        ni = in.readString();
        sc = in.readString();
        ac = in.readString();
        id = in.readString();
        queue = in.readString();
        nric = in.readString();
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(drug);
        parcel.writeString(fast);
        parcel.writeString(adm);
        parcel.writeString(ni);
        parcel.writeString(sc);
        parcel.writeString(ac);
        parcel.writeString(id);
        parcel.writeString(queue);
        parcel.writeString(nric);
    }
}

