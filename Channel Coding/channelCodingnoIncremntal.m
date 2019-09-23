clc
clear all
close all

obj = VideoReader('highway.avi');
a= read(obj);
frames=get(obj,'NumberOfFrames');
%extracting Frames
for i=1:frames
     I(i).cdata=a(:,:,:,i);
end

s=size(I(1).cdata);
mov(1:frames) =struct('cdata', zeros(s(1),s(2), 3, 'uint8'),'cozlormap', []);

t = poly2trellis(7,[171 133]);

for Frame=1:frames
%Red Components of the Frame
R=I(Frame).cdata(:,:,1); 
%Green Components of the Frame
G=I(Frame).cdata(:,:,2); 
%Blue Components of the Frame
B=I(Frame).cdata(:,:,3);

[sz1,sz2]=size(R);
sequenceRed=reshape(R,1,sz1*sz2);

[sz1,sz2]=size(G);
sequenceGreen=reshape(G,1,sz1*sz2);

[sz1,sz2]=size(B);
sequenceBlue=reshape(B,1,sz1*sz2);

sequenceRed=double(sequenceRed);
sequenceRed=de2bi(sequenceRed);
sequenceRed=reshape(sequenceRed,1,[]);

sequenceGreen=double(sequenceGreen);
sequenceGreen=de2bi(sequenceGreen);
sequenceGreen=reshape(sequenceGreen,1,[]);

sequenceBlue=double(sequenceBlue);
sequenceBlue=de2bi(sequenceBlue);
sequenceBlue=reshape(sequenceBlue,1,[]);

sequence=horzcat(sequenceRed,sequenceGreen,sequenceBlue);
length(sequence);
length(sequence)/1024;

divided=[1:594,1:1024];
divided=reshape(sequence,594,1024);

decoded=zeros(594,1024);


for i=1:594
        code = convenc(divided(i,:),t);
        p=0.1;
        recieved = bsc(code,p);
        decoded(i,:) = vitdec(recieved,t,1,'trunc','hard');
end

sequenceDone=reshape(decoded,1,[]);
sequenceRedDone=sequenceDone(1,1:202752);
sequenceGreenDone=sequenceDone(1,202753:405504);
sequenceBlueDone=sequenceDone(1,405505:608256);

sequenceRedDone=reshape(sequenceRedDone,25344,8);
sequenceRedDone=bi2de(sequenceRedDone);
sequenceRedDone=uint8(sequenceRedDone);
sequenceRedDone=reshape(sequenceRedDone,144,176);

sequenceGreenDone=reshape(sequenceGreenDone,25344,8);
sequenceGreenDone=bi2de(sequenceGreenDone);
sequenceGreenDone=uint8(sequenceGreenDone);
sequenceGreenDone=reshape(sequenceGreenDone,144,176);

sequenceBlueDone=reshape(sequenceBlueDone,25344,8);
sequenceBlueDone=bi2de(sequenceBlueDone);
sequenceBlueDone=uint8(sequenceBlueDone);
sequenceBlueDone=reshape(sequenceBlueDone,144,176);

mov(1,Frame).cdata(:,:,1) = sequenceRedDone; 
mov(1,Frame).cdata(:,:,2) = sequenceGreenDone;
mov(1,Frame).cdata(:,:,3) = sequenceBlueDone;
end


%write the movie
writer = VideoWriter('prob0.1.avi','Uncompressed AVI');
writer.FrameRate=obj.FrameRate;
open(writer);
writeVideo(writer,mov);
close(writer);