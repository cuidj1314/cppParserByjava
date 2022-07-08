//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "Unit1.h"
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma link "Word_2K_SRVR"
#pragma resource "*.dfm"
TForm1 *Form1;
//---------------------------------------------------------------------------
__fastcall TForm1::TForm1(TComponent* Owner)
        : TForm(Owner)
{

    edtC_Id->Text ="";
    edtC_Name->Text ="";
    edtC_Sex->Text ="";
    edtC_Tel->Text ="";
    

    ListView1->ViewStyle = vsReport;
 

    ListView1->Columns->Clear();
    

    ListTitleCreat();
}
//---------------------------------------------------------------------------

void TForm1::ShowData(){


    customer->Open();
    edtC_Id->Text=customer->FieldValues["C_ID"];
    edtC_Name->Text=customer->FieldValues["C_NAME"];
    edtC_Sex->Text=customer->FieldValues["C_SEX"];
    edtC_Tel->Text=customer->FieldValues["C_TEL"];
}
//---------------------------------------------------------------------------

void TForm1::ListTitleCreat(){

    TListColumn *lc = ListView1->Columns->Add();
    lc->Caption = "";
    lc->Width = 120;
 

    lc = ListView1->Columns->Add();
    lc->Caption = "";
    lc->Width = 125;
 

    lc = ListView1->Columns->Add();
    lc->Caption = "";
    lc->Width = 125;


    lc = ListView1->Columns->Add();
    lc->Caption = "";
    lc->Width = 130;
}
//---------------------------------------------------------------------------

void TForm1::ListDataShow(){

    ListView1->Items->Clear();

    ListView1->ReadOnly=true;
    customer->Close();
    customer->Open();

    while(!customer->Eof){
        TListItem *li = ListView1->Items->Add();
        li->Caption = customer->FieldValues["C_ID"];
        li->SubItems->Add(customer->FieldValues["C_NAME"]);
        li->SubItems->Add(customer->FieldValues["C_SEX"]);
        li->SubItems->Add(customer->FieldValues["C_TEL"]);
        customer->Next();
    }
    
   
    ListView1->HotTrack=true;
   
    ListView1->RowSelect = true;
    customer->Close();
}
//---------------------------------------------------------------------------

void __fastcall TForm1::BtnFristClick(TObject *Sender)
{
    customer->Close();
    customer->Open();
    customer->First();
   
    ShowData();
    customer->Close();
}
//---------------------------------------------------------------------------

void __fastcall TForm1::BtnNextClick(TObject *Sender)
{
    customer->Open();
    customer->Next();
    ShowData();
}
//---------------------------------------------------------------------------

void __fastcall TForm1::BtnPriorClick(TObject *Sender)
{
    customer->Open();
    customer->Prior();
    ShowData();
}
//---------------------------------------------------------------------------

void __fastcall TForm1::BtnChangeClick(TObject *Sender)
{
    if(MessageBox(NULL,"",33)==IDOK){
        customer->Edit();
        customer->FieldValues["C_ID"]=edtC_Id->Text;
        customer->FieldValues["C_NAME"]=edtC_Name->Text;
        customer->FieldValues["C_SEX"]=edtC_Sex->Text;
        customer->FieldValues["C_TEL"]=edtC_Tel->Text;
        customer->Post();
        ShowData();
        BtnChange->Caption="";
        ListDataShow();
    }
}
//---------------------------------------------------------------------------

void __fastcall TForm1::BtnDeleteClick(TObject *Sender)
{
    if(MessageBox(NULL,"",49)==IDOK){
        customer->Open();
        customer->Delete();
        ListDataShow();
    }
}
//---------------------------------------------------------------------------

void __fastcall TForm1::BtnAddClick(TObject *Sender)
{
    customer->Open();
    ShowMessage("B");
   
    edtC_Id->Text ="";
    edtC_Name->Text ="";
    edtC_Sex->Text ="";
    edtC_Tel->Text ="";
    BtnChange->Caption="?хо";
    customer->Append();
}
//---------------------------------------------------------------------------


void __fastcall TForm1::BtnListClick(TObject *Sender)
{
    ListDataShow();

}
//---------------------------------------------------------------------------


void __fastcall TForm1::BtnEndClick(TObject *Sender)
{
    customer->Open();
    customer->Last();
   
    ShowData();
}
//---------------------------------------------------------------------------
void __fastcall TForm1::BtnSelectClick(TObject *Sender)
{
    
    if(ListView1->Selected != NULL){
       
        int i =ListView1->Selected->Index;
        edtC_Id->Text = ListView1->Selected->Caption;
        edtC_Name->Text = ListView1->Items->Item[i]->SubItems->Strings[0];
        edtC_Sex->Text = ListView1->Items->Item[i]->SubItems->Strings[1];
        edtC_Tel->Text = ListView1->Items->Item[i]->SubItems->Strings[2];
        customer->Open();
        customer->MoveBy(i);
        
    } else {
         ShowMessage("");
    }
}
//---------------------------------------------------------------------------

void __fastcall TForm1::BtnExitClick(TObject *Sender)
{
    customer->Close();
    Close();
}
//---------------------------------------------------------------------------
void __fastcall TForm1::BtnPrivewClick(TObject *Sender)
{
        if(customer->Active==false){
            customer->Active=true;
            Form2->QuickRep1->PreviewModal();
        }
}
//---------------------------------------------------------------------------

